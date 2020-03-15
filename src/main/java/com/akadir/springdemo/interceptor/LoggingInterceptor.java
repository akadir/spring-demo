package com.akadir.springdemo.interceptor;

import com.akadir.springdemo.enumeration.LogAttribute;
import com.akadir.springdemo.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
        requestServlet.setAttribute(LogAttribute.ENTER_SERVICE_KEY.getName(), System.currentTimeMillis());
        logger.info("set up mdc before request to: {}", requestServlet.getRequestURI());
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
        Long duration = null;

        Optional<Object> optionalStartTime = Optional.ofNullable(request.getAttribute(LogAttribute.ENTER_SERVICE_KEY.getName()));

        if (optionalStartTime.isPresent()) {
            long startTime = (long) optionalStartTime.get();
            request.removeAttribute(LogAttribute.ENTER_SERVICE_KEY.getName());
            long endTime = System.currentTimeMillis();
            duration = (endTime - startTime);
        }


        logger.info("tear down mdc for request to: {} | after: {} ms", request.getRequestURI(), duration);
        MDCUtil.tearDownMDC();
    }
}
