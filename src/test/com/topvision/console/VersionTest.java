/***********************************************************************
 * $Id: VersionTest.java,v1.0 2011-7-12 上午09:47:08 $
 * 
 * @author: Victor
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.topvision.console;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Victor
 * @created @2011-7-12-上午09:47:08
 * 
 */
public class VersionTest {
    /**
     * Test method for {@link com.topvision.framework.Version#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("V1.0.0_B63_D20110712-09:44:12 by Victor", new com.topvision.framework.Version().toString());
    }

    /**
     * Test method for {@link com.topvision.framework.Version#Version()}.
     */
    @Test
    public void testVersion() {
    }
}
