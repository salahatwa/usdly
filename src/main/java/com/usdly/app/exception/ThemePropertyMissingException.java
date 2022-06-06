package com.usdly.app.exception;

/**
 * Theme property missing exception.
 *
 * @author ssatwa
 * @date 4/11/19
 */
public class ThemePropertyMissingException extends BadRequestException {

    public ThemePropertyMissingException(String message) {
        super(message);
    }

    public ThemePropertyMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
