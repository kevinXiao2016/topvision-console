/***********************************************************************
 * $Id: JConsoleService.java,v1.0 2011-11-25 上午11:55:21 $
 * 
 * @author: Victor
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.topvision.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;

import com.topvision.console.event.Deployable;
import com.topvision.console.server.jetty.EmbeddedJettyDeployer;
import com.topvision.framework.common.Configuration;
import com.topvision.framework.common.ConfigurationFactory;

/**
 * @author Victor
 * @created @2011-11-25-上午11:55:21
 * 
 */
public class JConsoleService {
    protected static final Logger logger = LoggerFactory.getLogger(JConsoleService.class);
    private Deployable deployable = null;

    private void startService() {
        logger.info("JConsoleService startService......");
        ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory
                .getILoggerFactory();
        loggerContext.getStatusManager().add(new StatusListener() {
            @Override
            public void addStatusEvent(Status status) {
                logger.error("Logback status:{}", status);
            }
        });
        deployable = new EmbeddedJettyDeployer();
        registryListener();
        deployable.startup();
    }

    private void stopService() {
        try {
            logger.info("JConsoleService stopService......");
            Configuration config = ConfigurationFactory
                    .buildPropertiesFileConfiguration(JConsoleConstants.CONFIG_LOCATION);
            Socket s = new Socket("127.0.0.1", config.getInt("jconsole.listener.port", 3001));
            s.getOutputStream().write("stop".getBytes());
            s.getOutputStream().flush();
            s.close();
        } catch (Throwable e) {
            logger.error("stopService", e);
        }
    }

    /**
     * 注册重启/关闭监听器
     */
    private void registryListener() {
        logger.info("JConsoleService registryListener : {}",
                deployable.getConfig().getInt("jconsole.listener.port", 3001));
        Thread thread = new Thread() {
            @Override
            public void run() {
                setName("JConsoleServiceListener");
                ServerSocket server = null;
                try {
                    server = new ServerSocket(deployable.getConfig().getInt("jconsole.listener.port", 3001));
                    while (true) {
                        Socket s = server.accept();
                        logger.info("JConsoleService listener accept a socket:{}", s.toString());
                        InputStream inputstream = s.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                        String line = reader.readLine();
                        logger.info("JConsoleService listener get a command:{}", line);
                        if ("stop".equalsIgnoreCase(line) || "shutdown".equalsIgnoreCase(line)) {
                            try {
                                logger.info("The system exit immediately!!!");
                                //关闭太慢，直接退出
                                //deployable.shutdown();
                                System.exit(0);
                            } catch (Exception ex) {
                                logger.error("shutdown", ex);
                            }
                        } else if ("query".equalsIgnoreCase(line)) {
                            try {
                                logger.info("Query server info!");
                                s.getOutputStream().write(deployable.toString().getBytes());
                                s.getOutputStream().write("\n".getBytes());
                                s.getOutputStream().flush();
                            } catch (Exception ex) {
                                logger.error("query", ex);
                            }
                        }
                        s.close();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                        }
                    }
                } catch (BindException ex) {
                    logger.error("bind error", ex);
                } catch (IOException ex) {
                    logger.error("io error", ex);
                } finally {
                    try {
                        server.close();
                    } catch (IOException e) {
                        logger.error("", e);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0 || "start".equals(args[0])) {
            new JConsoleService().startService();
        } else if ("stop".equals(args[0])) {
            new JConsoleService().stopService();
        }
    }
}
