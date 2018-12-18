/**
 * 
 */
package com.topvision.console.action;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.topvision.console.JConsoleApp;
import com.topvision.framework.common.ResourceManager;
import com.topvision.framework.swing.SwingComponentFactory;
import com.topvision.framework.swing.event.AbstractItemAction;

/**
 * @author niejun
 * 
 */
public class ExitAction extends AbstractItemAction {
    private static final long serialVersionUID = -8640439638277577789L;

    private static String getString(String key) {
        return ResourceManager.getString(key);
    }

    public ExitAction() {
        super(null, ResourceManager.getString("statusinfo.exit"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (SwingComponentFactory.showConfirmDialog(null, getString("label.exit"), getString("label.tip"),
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                JConsoleApp.getApp().shutdownService();
            } catch (Exception ex) {
            }
            System.exit(0);
        }
    }
}
