package com.akadir.springdemo.entity.enumeration;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:15
 */
public enum LogKey {
    REQUEST_ID("request-id"), REMOTE_ADDRESS("remote-address"), REMOTE_PORT("remote-port"),
    REMOTE_HOST("remote-host"), FORWARDED_ADDRESS("x-forwarded-for"), USER_ID("user-id"),
    HOST_IP("host-ip");

    private final String value;

    LogKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
