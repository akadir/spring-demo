package com.akadir.springdemo.error;

import com.akadir.springdemo.exception.base.SpringDemoException;
import com.akadir.springdemo.util.MDCUtil;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author akadir
 * Date: 15.03.2020
 * Time: 01:15
 */
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Throwable t = super.getError(webRequest);

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        if (t instanceof SpringDemoException) {
            updateErrorAttributes(errorAttributes, (SpringDemoException) t, webRequest);
        }

        errorAttributes.put("trace-id", MDCUtil.getRequestId());

        return errorAttributes;
    }

    private void updateErrorAttributes(Map<String, Object> errorAttributes, SpringDemoException e, RequestAttributes requestAttributes) {
        requestAttributes.setAttribute("javax.servlet.error.status_code", e.getHttpStatus().value(), 0);
        errorAttributes.put("status", e.getHttpStatus().value());
        errorAttributes.put("error", e.getHttpStatus().getReasonPhrase());
    }
}
