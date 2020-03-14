package com.akadir.springdemo.aspect;

import com.akadir.springdemo.annotation.Loggable;
import com.akadir.springdemo.entity.enumeration.LogLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author akadir
 * Date: 3.03.2020
 * Time: 21:00
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "@within(loggable)")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint, Loggable loggable) throws Throwable {
        String classNameAndMethodName = getClassNameAndMethodName(proceedingJoinPoint);

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        if (isLogLevelForLoggableInfo(loggable)) {
            LOGGER.info("Execution time of {} is: {} ms", classNameAndMethodName, stopWatch.getTotalTimeMillis());
        } else if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Execution time of {} is: {} ms", classNameAndMethodName, stopWatch.getTotalTimeMillis());
        }

        return result;
    }

    @Before(value = "@within(loggable)")
    public void logInputParametersOfMethods(JoinPoint joinPoint, Loggable loggable) {
        String classNameAndMethodName = getClassNameAndMethodName(joinPoint);
        Object[] signatureArgs = joinPoint.getArgs();

        if (isLogLevelForLoggableInfo(loggable)) {
            LOGGER.info("Input parameters of {} are: {}", classNameAndMethodName, signatureArgs);
        } else if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Input parameters of {} are: {}", classNameAndMethodName, signatureArgs);
        }
    }

    @AfterReturning(value = "@within(loggable)", returning = "result")
    public void logReturnParametersOfMethods(JoinPoint joinPoint, Loggable loggable, Object result) {
        String classNameAndMethodName = getClassNameAndMethodName(joinPoint);

        if (isLogLevelForLoggableInfo(loggable)) {

            if (result != null) {
                LOGGER.info("Return parameters of {} is: {}", classNameAndMethodName, result);
            } else {
                LOGGER.info("Return parameters of {} is null value.", classNameAndMethodName);
            }
        } else if (LOGGER.isDebugEnabled()) {
            if (result != null) {
                LOGGER.debug("Return parameters of {} is: {}", classNameAndMethodName, result);
            } else {
                LOGGER.debug("Return parameters of {} is null value.", classNameAndMethodName);
            }
        }
    }

    private boolean isLogLevelForLoggableInfo(Loggable loggable) {
        return loggable.type().getLogLevel() == LogLevel.INFO;
    }

    private String getClassNameAndMethodName(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        return className + "." + methodName;
    }
}
