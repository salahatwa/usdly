package com.usdly.app.exception;

/**
 * Not install exception.
 *
 * @author ssatwa
 * @date 19-4-29
 */
public class NotInstallException extends BadRequestException {

    public NotInstallException(String message) {
        super(message);
    }

    public NotInstallException(String message, Throwable cause) {
        super(message, cause);
    }
}
