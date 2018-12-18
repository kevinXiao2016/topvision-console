/**
 * 
 */
package com.topvision.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import com.topvision.framework.common.ResourceManager;
import com.topvision.framework.swing.JController;
import com.topvision.framework.swing.JDlg;

/**
 * @author niejun
 * 
 */
public class AboutController extends JController<AboutView> {
    private JDialog dialog = null;
    private AboutView view = null;

    public AboutController() {
        view = new AboutView();
        initListener();
        initData();
    }

    private String getString(String key) {
        return ResourceManager.getString(key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.framework.swing.JController#getView()
     */
    @Override
    public AboutView getView() {
        return null;
    }

    private void initData() {
        view.jlPic.setIcon(ResourceManager.getImage("about.gif"));
        view.jlAbout.setText(getString("about.desc"));
    }

    private void initListener() {
        view.jbOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.framework.swing.JController#showView()
     */
    @Override
    public void showView() {
        dialog = new JDlg((JDialog) null, true);
        dialog.setTitle(getString("about.title"));
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(view);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setVisible(true);
        dialog.dispose();
    }
}
