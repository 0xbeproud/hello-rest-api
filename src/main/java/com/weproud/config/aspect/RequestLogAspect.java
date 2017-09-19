package com.weproud.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Logan. k
 */
@Slf4j
@Component
@Aspect
public class RequestLogAspect {
    @Before("within(com.weproud.controller..*)")
    private void before(JoinPoint joinPoint) {
        String caller = joinPoint.getSignature().toShortString();
        log.info(caller + " method called.");
    }
}
