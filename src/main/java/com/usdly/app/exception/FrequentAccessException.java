package com.usdly.app.exception;

/**
 * Frequent access exception.
 *
 * @author ssatwa
 * @date 3/28/19
 */
public class FrequentAccessException extends BadRequestException {

    public FrequentAccessException(String message) {
        super(message);
    }

    public FrequentAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
