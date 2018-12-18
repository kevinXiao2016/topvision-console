/**
 * 
 */
package com.topvision.console.server.jetty;

import java.io.File;
import java.io.InputStream;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle.AbstractLifeCycleListener;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topvision.console.JConsoleConstants;
import com.topvision.console.event.DeployerAdapter;
import com.topvision.console.exception.DeployException;
import com.topvision.framework.common.Configuration;
import com.topvision.framework.common.ConfigurationFactory;
import com.topvision.license.parser.LicenseJob;

/**
 * @author niejun
 * 
 */
public class EmbeddedJettyDeployer extends DeployerAdapter {
    private static Logger logger = LoggerFactory.getLogger(EmbeddedJettyDeployer.class);
    private Server server = null;
    private Configuration config;
    private LicenseJob licenseJob;
    private Long startTime;

    public EmbeddedJettyDeployer() {
        try {
            startTime = System.currentTimeMillis();
            config = ConfigurationFactory.buildPropertiesFileConfiguration(JConsoleConstants.CONFIG_LOCATION);
            if (logger.isInfoEnabled()) {
                logger.info(config.toString());
            }
            System.getProperties().put("org.apache.jasper.compiler.disablejsr199",
                    config.getString("org.apache.jasper.compiler.disablejsr199", "true"));
            String jetty_home = config.getString("jetty.home", "../");
            String webPath = config.getString("webapp.war", "/webapps/ems.war");
            int port = config.getInt("http.port", 8080);

            if (logger.isInfoEnabled()) {
                logger.info(jetty_home + webPath);
            }
            server = new Server(port);
            WebAppContext webapp = new WebAppContext();
            File tmp = new File(config.getString("temp.path", "../tmp"));
            if (!tmp.exists()) {
                tmp.mkdir();
            }
            webapp.setTempDirectory(tmp);

            webapp.setContextPath(config.getString("http.context.path", "/"));
            webapp.setWar(jetty_home + webPath);
            webapp.setWelcomeFiles(new String[] { "index.jsp", "index.html" });
            webapp.setExtractWAR(config.getBoolean("extract.war", true));
            webapp.setCopyWebDir(false);
            webapp.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer",
                    config.getString("useFileMappedBuffer", "false"));
            webapp.getInitParams().put("org.eclipse.jetty.servlet.Default.dirAllowed",
                    config.getString("dirAllowed", "false"));
            webapp.setMaxFormContentSize(-1);
            // 禁止jetty锁定 javascript,css等静态资源
            webapp.setSessionHandler(new SessionHandler(new HashSessionManager()));
            DefaultServlet defaultServlet = new DefaultServlet();
            ServletHolder holder = new ServletHolder(defaultServlet);
            webapp.addServlet(holder, "/");

            // 配置 jetty server的handler
            server.setHandler(webapp);

            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port);
            connector.setIdleTimeout(config.getLong("request.maxIdleTime", 300000));
            int processors = Runtime.getRuntime().availableProcessors();
            if (processors > 2) {
                connector.setAcceptQueueSize(processors * config.getInt("request.thread.pool", 50));
            } else {
                connector.setAcceptQueueSize(config.getInt("request.max.pool", 100));
            }
            server.setConnectors(new Connector[] { connector });
            server.addLifeCycleListener(new AbstractLifeCycleListener() {
                @Override
                public void lifeCycleStarted(LifeCycle cycle) {
                    Long duration = System.currentTimeMillis() - startTime;
                    logger.info("Server Started with {}ms", duration);
                    System.out.println("Server Started with " + duration + "ms");
                }

                @Override
                public void lifeCycleStopping(LifeCycle cycle) {
                    logger.info("Server({}) Stopping......", cycle);
                }
            });
            // add by bravin@20150715：设置request表单允许的最大字节数为不限制
            connector.getServer().setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", -1);
            // license定时程序
            licenseJob = new LicenseJob();
            licenseJob.setLicPath(webPath + "/WEB-INF/license.lic");
        } catch (Exception ex) {
            logger.error("Get local host address.", ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.DeployerAdapter#restart()
     */
    @Override
    public void restart() throws DeployException {
        try {
            shutdown();
            Thread.sleep(5000);
            startup();
        } catch (Exception ex) {
            throw new DeployException("Restart Embedded Jetty unsuccessfully.", ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.DeployerAdapter#shutdown()
     */
    @Override
    public InputStream shutdown() throws DeployException {
        try {
            server.stop();
            licenseJob.stop();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DeployException("Stop Embedded Jetty unsuccessfully.", ex);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.DeployerAdapter#startup()
     */
    @Override
    public InputStream startup() throws DeployException {
        try {
            server.start();
            licenseJob.start();
            server.join();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DeployException("Start Embedded Jetty unsuccessfully.", ex);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.Deployable#getConfig()
     */
    @Override
    public Configuration getConfig() {
        return config;
    }
}
