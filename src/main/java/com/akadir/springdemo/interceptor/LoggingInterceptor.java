package com.akadir.springdemo.interceptor;

import com.akadir.springdemo.entity.enumeration.LogKey;
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
        requestServlet.setAttribute(LogKey.ENTER_REST_SERVICE.getValue(), System.currentTimeMillis());
        MDCUtil.setUpMDC(requestServlet);
        logger.info("preHandle - to: {}", requestServlet.getRequestURI());
        return super.preHandle(requestServlet, responseServlet, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            // there are cases where this handler isn't an instance of HandlerMethod, so the cast fails.
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String controllerName = handlerMethod.getBean().getClass().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();
            logger.info("postHandle - method matched: {} - {}", controllerName, methodName);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        Long duration = null;

        Optional<Object> startTime = Optional.ofNullable(request.getAttribute(LogKey.ENTER_REST_SERVICE.getValue()));

        if (startTime.isPresent()) {
            request.removeAttribute(LogKey.ENTER_REST_SERVICE.getValue());
            long endTime = System.currentTimeMillis();
            duration = (endTime - (Long) startTime.get());
        }

        logger.info("requestEnd - To: {} - Duration: {} ms", request.getRequestURI(), duration);
        MDCUtil.tearDownMDC();
    }
}
