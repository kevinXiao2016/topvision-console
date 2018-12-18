/**
 * 
 */
package com.topvision.console.action;

import java.awt.event.ActionEvent;

import com.topvision.console.AboutController;
import com.topvision.framework.common.ResourceManager;
import com.topvision.framework.swing.event.AbstractItemAction;

/**
 * @author niejun
 * 
 */
public class AboutAction extends AbstractItemAction {
    private static final long serialVersionUID = 3117839269991165614L;

    public AboutAction() {
        super(null, ResourceManager.getString("statusinfo.about"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AboutController about = new AboutController();
        about.showView();
    }
}
