package com.ayshiktest.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.ayshiktest.controller.*.*(..))")
    public void myPointcut() { }

    @Around("myPointcut()")
    public Object applogger(ProceedingJoinPoint pjp) {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] args = pjp.getArgs();
        try {
            logger.info("Method Invoked : " + className + " : " + methodName + " : args : " + objectMapper.writeValueAsString(args));
        } catch (JsonProcessingException e) {
            logger.info("Method Invoked : " + className + " : " + methodName);
        }
        Object methodResponse = null;
        try {
            methodResponse = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        try {
            logger.info(className + " : " + methodName + " : response : " + objectMapper.writeValueAsString(methodResponse));
        } catch (JsonProcessingException e) {
            logger.info(className + " : " + methodName);
        }
        return methodResponse;
    }
}
