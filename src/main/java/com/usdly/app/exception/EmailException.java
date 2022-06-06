package com.usdly.app.exception;

/**
 * Email exception.
 *
 * @author ssatwa
 */
public class EmailException extends ServiceException {

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
