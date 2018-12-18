/**
 * 
 */
package com.topvision.console.event;

import java.io.InputStream;

import com.topvision.console.exception.DeployException;

/**
 * @author niejun
 * 
 */
public abstract class DeployerAdapter implements Deployable {
    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.Deployable#restart()
     */
    @Override
    public void restart() throws DeployException {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.Deployable#shutdown()
     */
    @Override
    public InputStream shutdown() throws DeployException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.Deployable#startup()
     */
    @Override
    public InputStream startup() throws DeployException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.topvision.console.event.Deployable#startupInSeparateWindow()
     */
    @Override
    public void startupInSeparateWindow() throws DeployException {
    }
}
