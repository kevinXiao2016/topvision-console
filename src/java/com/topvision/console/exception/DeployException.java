/**
 * 
 */
package com.topvision.console.exception;

import com.topvision.framework.exception.TopvisionRuntimeException;

/**
 * @author niejun
 * 
 */
public class DeployException extends TopvisionRuntimeException {
    private static final long serialVersionUID = 5374771521239500494L;

    public DeployException(String msg) {
        super(msg);
    }

    public DeployException(String msg, Throwable th) {
        super(msg, th);
    }
}
