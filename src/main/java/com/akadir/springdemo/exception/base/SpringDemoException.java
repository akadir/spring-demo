package com.akadir.springdemo.exception.base;

import org.springframework.http.HttpStatus;

/**
 * @author akadir
 * Date: 14.03.2020
 * Time: 23:38
 */
public abstract class SpringDemoException extends RuntimeException {
    private final HttpStatus httpStatus;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public SpringDemoException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
