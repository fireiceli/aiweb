package com.aichat.aiweb.Aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {
    @Around("execution(* com.aichat.aiweb.Service..*(..))")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable{
        try{
            return joinPoint.proceed();
        }
        catch (Exception e){
            log.error("Exception in method {}: {}", joinPoint.getSignature().getName(), e.getMessage());
            return null;
        }
    }
}
