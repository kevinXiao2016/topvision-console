/**
 * 
 */
package com.topvision.console;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;

import com.topvision.framework.common.ResourceManager;

/**
 * @author niejun
 * 
 */
public class TrayIconView {
    TrayIcon trayIcon = null;
    MenuItem miOpenWeb = null;
    MenuItem miAbout = null;
    MenuItem miStart = null;
    MenuItem miStop = null;
    MenuItem miRestart = null;
    MenuItem miExit = null;
    Image lightImg = null;
    Image grayImg = null;

    TrayIconView() {
        onCreate();
    }

    private void onCreate() {
        PopupMenu popup = new PopupMenu();

        miOpenWeb = new MenuItem(getString("menuitem.open.web"));
        popup.add(miOpenWeb);
        popup.addSeparator();
        miStart = new MenuItem(getString("menuitem.start"));
        popup.add(miStart);
        miStop = new MenuItem(getString("menuitem.stop"));
        popup.add(miStop);
        miRestart = new MenuItem(getString("menuitem.restart"));
        popup.add(miRestart);
        popup.addSeparator();

        miAbout = new MenuItem(getString("menuitem.about"));
        popup.add(miAbout);
        miExit = new MenuItem(getString("menuitem.exit"));
        popup.add(miExit);

        lightImg = ResourceManager.getImage("/META-INF/images/started.png").getImage();
        grayImg = ResourceManager.getImage("/META-INF/images/stopped.png").getImage();
        trayIcon = new TrayIcon(grayImg, getString("trayicon.tooltip"), popup);
    }

    private static String getString(String key) {
        return ResourceManager.getString(key);
    }
}
