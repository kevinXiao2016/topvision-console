/**
 * 
 */
package com.topvision.console.event;

import java.io.InputStream;

import com.topvision.console.exception.DeployException;
import com.topvision.framework.common.Configuration;

/**
 * @author niejun
 * 
 */
public interface Deployable {
    void restart() throws DeployException;

    InputStream shutdown() throws DeployException;

    InputStream startup() throws DeployException;

    void startupInSeparateWindow() throws DeployException;

    Configuration getConfig();
}
