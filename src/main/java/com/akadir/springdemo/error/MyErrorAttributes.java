package com.akadir.springdemo.error;

import com.akadir.springdemo.enumeration.ErrorAttributesKey;
import com.akadir.springdemo.exception.base.SpringDemoException;
import com.akadir.springdemo.util.MDCUtil;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
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
        } else if (t instanceof EntityNotFoundException) {
            HttpStatus noContent = HttpStatus.NO_CONTENT;
            webRequest.setAttribute(ErrorAttributesKey.WEB_REQUEST_STATUS_CODE.key(), noContent.value(), 0);
            errorAttributes.put(ErrorAttributesKey.STATUS.key(), noContent.value());
            errorAttributes.put(ErrorAttributesKey.ERROR.key(), noContent.getReasonPhrase());
        }

        errorAttributes.put(ErrorAttributesKey.TRACE_ID.key(), MDCUtil.getRequestId());

        return errorAttributes;
    }

    private void updateErrorAttributes(Map<String, Object> errorAttributes, SpringDemoException e,
                                       RequestAttributes requestAttributes) {
        requestAttributes.setAttribute(ErrorAttributesKey.WEB_REQUEST_STATUS_CODE.key(), e.getHttpStatus().value(), 0);
        errorAttributes.put(ErrorAttributesKey.STATUS.key(), e.getHttpStatus().value());
        errorAttributes.put(ErrorAttributesKey.ERROR.key(), e.getHttpStatus().getReasonPhrase());
    }
}
