package com.akadir.springdemo.controller.error;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author akadir
 * Date: 15.03.2020
 * Time: 01:38
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class MyErrorController extends AbstractErrorController {
    private final ErrorProperties errorProperties;

    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes);
        this.errorProperties = errorProperties;
    }


    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = this.getErrorAttributes(request, this.isIncludeStackTrace(request));
        HttpStatus status = this.getStatus(request);

        return new ResponseEntity<>(body, status);
    }

    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = this.errorProperties.getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        } else {
            return include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM && this.getTraceParameter(request);
        }
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

}
