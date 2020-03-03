package com.akadir.springdemo.entity.enumeration;

/**
 * @author akadir
 * Date: 3.03.2020
 * Time: 22:59
 */
public enum LoggableType {
    CONTROLLER(LogLevel.INFO), SERVICE(LogLevel.DEBUG), REPOSITORY(LogLevel.DEBUG), COMPONENT(LogLevel.DEBUG),
    OTHER(LogLevel.DEBUG);

    private LogLevel logLevel;

    LoggableType(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }
}
