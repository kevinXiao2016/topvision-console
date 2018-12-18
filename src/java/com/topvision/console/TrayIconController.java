/*
 * @(#)TrayIconController.java
 *
 * Copyright 2007 by zeta, All rights reserved.
 */
package com.topvision.console;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topvision.framework.common.Configuration;
import com.topvision.framework.common.ResourceManager;
import com.topvision.framework.common.RunCmd;
import com.topvision.framework.swing.JBrowserLauncher;
import com.topvision.framework.swing.SwingComponentFactory;

/**
 * @author niejun
 */
public class TrayIconController {
    private static Logger logger = LoggerFactory.getLogger(TrayIconController.class);
    TrayIconView view = null;

    public TrayIconController() {
        view = new TrayIconView();
        initData();
        initListener();
    }

    public void displayMessage(String caption, String text, TrayIcon.MessageType messageType) {
        view.trayIcon.displayMessage(caption, text, messageType);
    }

    private void initData() {
        Configuration configuration = JConsoleApp.getApp().getConfiguration();
        String port = configuration.getString("http.port", "3000");
        view.trayIcon.setToolTip(getString("trayicon.tooltip").replace("{0}", port));
    }

    private void initListener() {
        view.miOpenWeb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Configuration configuration = JConsoleApp.getApp().getConfiguration();
                    JBrowserLauncher.openURL(configuration.getString("home.url", "http://localhost:3000"));
                } catch (IOException e1) {
                    logger.error("", e1);
                }
            }
        });

        view.miStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Configuration config = JConsoleApp.getApp().getConfiguration();
                    int port = config.getInt("jconsole.listener.port", 3001);
                    Socket socket = new Socket("localhost", port);
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.println("start");
                    writer.flush();
                    writer.close();
                    socket.close();
                    view.trayIcon.setImage(view.lightImg);
                } catch (UnknownHostException e1) {
                    logger.error("", e1);
                } catch (IOException e1) {
                    logger.error("", e1);
                }
            }
        });

        view.miStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Configuration config = JConsoleApp.getApp().getConfiguration();
                    int port = config.getInt("jconsole.listener.port", 3001);
                    Socket socket = new Socket("localhost", port);
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.println("stop");
                    writer.flush();
                    writer.close();
                    socket.close();
                    view.trayIcon.setImage(view.grayImg);
                } catch (UnknownHostException e1) {
                    logger.error("", e1);
                } catch (IOException e1) {
                    logger.error("", e1);
                }
            }
        });

        view.miRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Configuration config = JConsoleApp.getApp().getConfiguration();
                    int port = config.getInt("jconsole.listener.port", 3001);
                    Socket socket = new Socket("localhost", port);
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.println("restart");
                    writer.flush();
                    writer.close();
                    socket.close();
                } catch (UnknownHostException e1) {
                    logger.error("", e1);
                } catch (IOException e1) {
                    logger.error("", e1);
                }
            }
        });

        view.miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (SwingComponentFactory.showConfirmDialog(null, getString("label.exit"), getString("label.tip"),
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
//                    try {
//                        Configuration config = JConsoleApp.getApp().getConfiguration();
//                        int port = config.getInt("jconsole.listener.port", 3001);
//                        Socket socket = new Socket("localhost", port);
//                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//                        writer.println("shutdown");
//                        writer.flush();
//                        writer.close();
//                        socket.close();
//                        Thread.sleep(5000);
//                    } catch (Exception e) {
//                        logger.error("", e);
//                    }
                    System.exit(1);
                }
            }
        });

        view.miAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Configuration configuration = JConsoleApp.getApp().getConfiguration();
                    if (configuration.getBoolean("gui.mode", false)) {
                        AboutController about = new AboutController();
                        about.showView();
                    } else {
                        JBrowserLauncher.openURL(configuration
                                .getString("about.url", "http://localhost:3000/about.jsp"));
                    }
                } catch (IOException e1) {
                    logger.error("", e1);
                }
            }
        });
    }

    public void loadingCompleted() {
        RunCmd cmd = new RunCmd("sc query \"nm3000 service\"");
        cmd.run();
        String result = cmd.getStdout();
        logger.error(result);
        if (result.indexOf("4  RUNNING") != -1) {
            view.trayIcon.setImage(view.lightImg);
            view.trayIcon.displayMessage(getString("app.title"), getString("label.is.running"),
                    TrayIcon.MessageType.NONE);
        } else if (result.indexOf("1  STOPPED") != -1) {
            view.trayIcon.setImage(view.grayImg);
            view.trayIcon.displayMessage(getString("app.title"), getString("label.is.stopped"),
                    TrayIcon.MessageType.NONE);
        } else if (result.indexOf("OpenService FAILED 1060") != -1) {
            if (JConsoleApp.getApp().isRunning()) {
                view.trayIcon.setImage(view.lightImg);
                view.trayIcon.displayMessage(getString("app.title"), getString("label.is.running"),
                        TrayIcon.MessageType.NONE);
            } else {
                view.trayIcon.setImage(view.grayImg);
                view.trayIcon.displayMessage(getString("app.title"), getString("label.is.stopped"),
                        TrayIcon.MessageType.NONE);
            }
        } else {
            view.trayIcon.displayMessage(getString("app.title"), getString("label.no.install"),
                    TrayIcon.MessageType.NONE);
        }
    }

    public void showView() {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            tray.add(view.trayIcon);
        } catch (AWTException e) {
            logger.error("", e);
        }
    }

    private static String getString(String key) {
        return ResourceManager.getString(key);
    }
}
