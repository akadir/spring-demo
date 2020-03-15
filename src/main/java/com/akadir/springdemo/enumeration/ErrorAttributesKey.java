package com.akadir.springdemo.enumeration;

/**
 * @author akadir
 * Date: 15.03.2020
 * Time: 03:09
 */
public enum ErrorAttributesKey {
    WEB_REQUEST_STATUS_CODE("javax.servlet.error.status_code"), STATUS("status"), ERROR("error"),
    TRACE_ID("trace-id");

    private String key;

    ErrorAttributesKey(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}