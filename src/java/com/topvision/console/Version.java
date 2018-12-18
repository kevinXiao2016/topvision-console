/***********************************************************************
 * $Id: Version.java,v1.0 2011-5-23 下午02:49:17 $
 * 
 * @author: Victor
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.topvision.console;

/**
 * @author Victor
 * @created @2011-5-23-下午02:49:17
 * 
 */
@com.topvision.framework.annotation.Database(module = "console", version = "1.0.1.0", date = "2012-10-10")
public class Version extends com.topvision.framework.Version {
    private static final long serialVersionUID = 2780829068384466042L;

    public Version() {
        setBuildTime("@20110101-12:00:00@");
        setBuildVersion("@V100R001@");
        setBuildNumber("@B001@");
        setBuildUser("@Victor@");
    }
}
