package com.example.ms.card.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class DtoLogger {
    @SneakyThrows
    @Around("@annotation(com.example.ms.card.aspect.annotation.LogDto)")
    public Object elapsedTimeLogger(ProceedingJoinPoint jp){
        Object[] args = jp.getArgs();
        String methodName = jp.getSignature().getName();
        log.info(">> requestDto of {}() is {}", methodName, Arrays.toString(args));
        var result = jp.proceed();
        log.info("<< responseDto of {}() is {}", jp.getSignature().getName(), result);
        return result;
    }
}
