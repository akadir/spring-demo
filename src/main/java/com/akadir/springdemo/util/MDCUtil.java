package com.akadir.springdemo.util;

import com.akadir.springdemo.entity.enumeration.LogKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.UUID;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:13
 */
public class MDCUtil {
    private static final Logger logger = LoggerFactory.getLogger(MDCUtil.class);

    private MDCUtil() {
    }

    static {
        try {
            String hostIp = InetAddress.getLocalHost().getHostAddress();
            MDC.put(LogKey.HOST_IP.getValue(), hostIp);
            logger.info("Set host-ip: {}", hostIp);
        } catch (Exception e) {
            logger.error("An error occurred while putting HOST_IP to MDC");
        }
    }

    public static void setUpMDC(HttpServletRequest servletRequest) {
        String remoteHost = servletRequest.getRemoteHost();
        String remoteAddr = servletRequest.getRemoteAddr();
        int remotePort = servletRequest.getRemotePort();
        String forwardedIp = servletRequest.getHeader("X-Forwarded-For");

        addRequestId();
        addForwardedIpIfExist(forwardedIp);
        addRemoteAddress(remoteAddr);
        addRemotePort(remotePort);
        addRemoteHost(remoteHost);
    }

    public static void tearDownMDC() {
        MDC.clear();
    }

    private static void addRemoteHost(String remoteHost) {
        MDC.put(LogKey.REMOTE_HOST.getValue(), remoteHost);
    }

    private static void addRemoteAddress(String remoteAddress) {
        MDC.put(LogKey.REMOTE_ADDRESS.getValue(), remoteAddress);
    }

    private static void addRemotePort(int remotePort) {
        MDC.put(LogKey.REMOTE_PORT.getValue(), String.valueOf(remotePort));
    }

    public static void addUserId(String userId) {
        MDC.put(LogKey.USER_ID.getValue(), userId);
    }

    private static void addRequestId() {
        String uniqueID = UUID.randomUUID().toString();
        MDC.put(LogKey.REQUEST_ID.getValue(), uniqueID);
    }

    private static void addForwardedIpIfExist(String forwardedIp) {
        if (forwardedIp != null && !forwardedIp.isEmpty()) {
            forwardedIp = forwardedIp.split(",")[0];
            MDC.put(LogKey.FORWARDED_ADDRESS.getValue(), forwardedIp);
        }
    }

    public static String getRequestId() {
        return MDC.get(LogKey.REQUEST_ID.getValue());
    }
}