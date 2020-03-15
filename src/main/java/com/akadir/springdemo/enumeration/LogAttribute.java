package com.akadir.springdemo.enumeration;

/**
 * @author akadir
 * Date: 15.03.2020
 * Time: 02:57
 */
public enum LogAttribute {
    ENTER_SERVICE_KEY("enter-service");

    private final String prefix = "log-attribute-prefix-";
    private String name;

    LogAttribute(String name) {
        this.name = prefix + name;
    }

    public String getName() {
        return name;
    }
}
