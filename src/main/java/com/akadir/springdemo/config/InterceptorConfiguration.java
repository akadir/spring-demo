package com.akadir.springdemo.config;

import com.akadir.springdemo.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author akadir
 * Date: 29.02.2020
 * Time: 17:08
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    LoggingInterceptor loggingInterceptor;

    @Autowired
    public InterceptorConfiguration(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}