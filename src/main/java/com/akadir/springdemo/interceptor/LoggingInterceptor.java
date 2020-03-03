package com.akadir.springdemo.interceptor;

import com.akadir.springdemo.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:09
 */
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {
        MDCUtil.setUpMDC(requestServlet);
        logger.debug("set up mdc before request to: {}", requestServlet.getRequestURI());
        return super.preHandle(requestServlet, responseServlet, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (!(handler instanceof HandlerMethod)) {
            logger.info("request to uri: {} not matched any method", request.getRequestURI());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        logger.debug("tear down mdc after request to: {}", request.getRequestURI());
        MDCUtil.tearDownMDC();
    }
}
