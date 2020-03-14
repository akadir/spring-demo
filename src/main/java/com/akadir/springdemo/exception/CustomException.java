package com.akadir.springdemo.exception;

import com.akadir.springdemo.exception.base.SpringDemoException;
import org.springframework.http.HttpStatus;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 19:43
 */
public class CustomException extends SpringDemoException {
    private static final String MESSAGE = "Custom exception message.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.I_AM_A_TEAPOT;

    public CustomException() {
        super(MESSAGE, HTTP_STATUS);
    }
}
