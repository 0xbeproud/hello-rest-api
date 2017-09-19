package com.weproud.config.aspect;

import com.weproud.config.interceptor.UserSessionStorage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Logan. k
 */
@Slf4j
@Component
@Aspect
public class RestControllerAspect {

    @Autowired
    private UserSessionStorage storage;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController() {
    }

    @Around("restController()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String requestId = this.storage.getRequestId();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();

        Signature signature = joinPoint.getSignature();
        log.info("signature: {}", signature.toLongString());

        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = joinPoint.proceed(); //핵심 기능 실행
            long end = System.currentTimeMillis();
            log.info("[{}] uri: {} {}, params: {}, taken: {}", requestId, method, uri, queryString, (end - start));
        } finally {
            // common logic
        }
        return result;
    }
}
