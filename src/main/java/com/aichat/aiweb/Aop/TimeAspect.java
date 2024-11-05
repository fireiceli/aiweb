package com.aichat.aiweb.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.aichat.aiweb.Service..*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long elapsedTime = System.nanoTime() - startTime;

        log.info("Method {} executed in {} ms", joinPoint.getSignature().getName(), elapsedTime / 1000000);
        return result;
    }
}
