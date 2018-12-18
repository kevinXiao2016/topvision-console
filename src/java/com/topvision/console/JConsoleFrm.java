/***********************************************************************
 * $Id: JConsoleFrm.java,v1.0 2012-7-19 上午10:41:57 $
 * 
 * @author: Victor
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.topvision.console;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topvision.framework.common.ResourceManager;

/**
 * @author Victor
 * @created @2012-7-19-上午10:41:57
 * 
 */
public class JConsoleFrm extends JFrame {
    private static final long serialVersionUID = -7063479472625215045L;
    private static Logger logger = LoggerFactory.getLogger(JConsoleFrm.class);

    public void init() {
        logger.info("JConsoleFrm.init...");
        this.setTitle(getString("frame.title"));
        setLayout(new BorderLayout());
        // set the preferred size of the demo
        setPreferredSize(new Dimension(800, 600));

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.doLayout();
    }

    private static String getString(String key) {
        return ResourceManager.getString(key);
    }
}
