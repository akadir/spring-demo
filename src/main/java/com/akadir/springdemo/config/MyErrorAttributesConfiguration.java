package com.akadir.springdemo.config;

import com.akadir.springdemo.error.MyErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author akadir
 * Date: 15.03.2020
 * Time: 01:27
 */
@Configuration
public class MyErrorAttributesConfiguration {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new MyErrorAttributes();
    }

    @Bean
    public ErrorProperties errorProperties() {
        return new ErrorProperties();
    }
}
