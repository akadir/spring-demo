package com.akadir.springdemo.annotation;

import com.akadir.springdemo.entity.enumeration.LoggableType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author akadir
 * Date: 3.03.2020
 * Time: 21:05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
    LoggableType type();

}