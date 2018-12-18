/*
 * @(#)JConsoleApp.java
 *
 * Copyright 2007 by zeta, All rights reserved.
 */
package com.topvision.console;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topvision.console.util.JdbcUtils;
import com.topvision.framework.common.Configuration;
import com.topvision.framework.common.ConfigurationFactory;
import com.topvision.framework.common.FileUtils;
import com.topvision.framework.common.ResourceManager;
import com.topvision.framework.swing.JAbstractApp;
import com.topvision.framework.swing.JBrowserLauncher;
import com.topvision.framework.swing.JSplashWindow;

/**
 * 应用包装器, 提供应用启动服务.
 * 
 * @author niejun
 */
public class JConsoleApp extends JAbstractApp {
    private static Logger logger = LoggerFactory.getLogger(JConsoleApp.class);
    private static JConsoleApp theApp = null;

    private TrayIconController trayIconController = null;
    private JSplashWindow splash = null;
    private JConsoleFrm frm;

    public static JConsoleApp getApp() {
        return theApp;
    }

    private static String getString(String key) {
        return ResourceManager.getString(key);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            theApp = new JConsoleApp();
            theApp.initialize();
        } catch (Exception e) {
            logger.error("jconsole", e);
        }
    }

    private JConsoleApp() throws Exception {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.framework.swing.JAbstractApp#getAppName()
     */
    @Override
    public final String getAppName() {
        return "Topvision Console";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.framework.swing.JAbstractApp#getConfigurationPath()
     */
    @Override
    public String getConfigurationPath() {
        String path = super.getConfigurationPath();
        if (path == null) {
            path = JConsoleConstants.CONFIG_LOCATION;
        }
        return path;
    }

    public void initialize() throws Exception {
        final Configuration configuration = ConfigurationFactory
                .buildPropertiesFileConfiguration(getConfigurationPath());
        setConfiguration(configuration);

        ResourceManager.addBundle(configuration.getString("resources.name", "console"));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        boolean splashMode = configuration.getBoolean("splash.mode", true);
        if (splashMode) {
            splash = new JSplashWindow(ResourceManager.getImage("../images/splash.gif"));
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    splash.setVisible(true);
                }
            });
        }
        frm = new JConsoleFrm();
        boolean systemTray = SystemTray.isSupported();
        if (systemTray) {
            trayIconController = new TrayIconController();
            trayIconController.showView();
            trayIconController.displayMessage(getString("app.title"), getString("label.loading"),
                    TrayIcon.MessageType.NONE);
        }

        // 增加关闭内置数据库回调
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (configuration.getBoolean("database.embedded", false)) {
                    try {
                        ProcessBuilder pb = new ProcessBuilder("stopMysql.bat", configuration.getString(
                                "database.port", "3306"), configuration.getString("database.user", "root"),
                                configuration.getString("database.passwd", ""));
                        pb.start();
                    } catch (Throwable ex) {
                    }
                }
            }
        });

        // 启动内置数据库
        if (configuration.getBoolean("database.embedded", false)) {
            BufferedReader reader = null;
            try {
                ProcessBuilder pb = new ProcessBuilder("startMysql.bat", configuration.getString("database.port",
                        "3306"));
                Process process = pb.start();
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String str = reader.readLine();
                while (str != null) {
                    str = reader.readLine();
                }
            } catch (Throwable ex) {
                logger.info("Start Embedded Mysql.", ex);
                JOptionPane.showMessageDialog(null, getString("db.start.unsuccessfully"));
                System.exit(1);
            } finally {
                FileUtils.closeQuitely(reader);
            }
        }

        // 探测数据库是否已经启动成功
        if (!JdbcUtils.loadDriver("com.mysql.jdbc.Driver")) {
            JOptionPane.showMessageDialog(null, getString("database.driver.error"));
            System.exit(1);
        }
        int count = 0;
        while (true) {
            count++;
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
            }
            if (JdbcUtils.connect(JdbcUtils.MYSQL, configuration.getString("database.host", "localhost"),
                    configuration.getString("database.port", "3306"), configuration.getString("database.user", "root"),
                    configuration.getString("database.passwd", "sa"))) {
                JdbcUtils.startJdbcConnTask(JdbcUtils.MYSQL, "localhost", "3306", "root", "");
                break;
            } else {
                if (count > 5
                        && JOptionPane.showConfirmDialog(null, getString("database.connect.error"), getString("tip"),
                                JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    System.exit(1);
                }
            }
        }

        if (systemTray) {
            trayIconController.loadingCompleted();
        }
        // 启动WEB APP
        try {
            if (configuration.getBoolean("auto.open.browser", true) && isRunning()) {
                JBrowserLauncher.openURL(configuration.getString("home.url", "http://localhost:3000"));
            }
        } catch (Exception ex) {
            logger.error("start webapp error", ex);
            JOptionPane.showMessageDialog(null, getString("web.start.unsuccessfully"));
            System.exit(1);
        }

        if (splash != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    splash.dispose();
                    frm.setVisible(true);
                }
            });
        }
    }

    public boolean isRunning() {
        try {
            Configuration config = JConsoleApp.getApp().getConfiguration();
            int port = config.getInt("jconsole.listener.port", 3001);
            Socket socket = new Socket("localhost", port);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println("query");
            writer.flush();
            byte[] buf = new byte[1024];
            socket.getInputStream().read(buf);
            writer.close();
            socket.close();
            logger.debug(new String(buf));
            return true;
        } catch (UnknownHostException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return false;
    }

    public void shutdownService() {
        try {
            Configuration config = getConfiguration();
            int port = config.getInt("jconsole.listener.port", 3001);
            Socket socket = new Socket("localhost", port);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println("start");
            writer.flush();
            writer.close();
            socket.close();
            trayIconController.view.trayIcon.setImage(trayIconController.view.lightImg);
        } catch (UnknownHostException e1) {
            logger.error("", e1);
        } catch (IOException e1) {
            logger.error("", e1);
        }
    }
}
