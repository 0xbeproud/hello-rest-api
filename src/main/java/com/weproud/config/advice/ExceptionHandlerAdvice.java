package com.weproud.config.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weproud.config.interceptor.UserSessionStorage;
import com.weproud.config.logback.TelegramSender;
import com.weproud.dto.ResponseBaseDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @Autowired
    private Environment environment;

    @Autowired
    private TelegramSender telegramSender;

    @Autowired
    private UserSessionStorage storage;

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity handleServiceException(HttpServletRequest request, ServiceException e) throws InterruptedException, ExecutionException, JsonProcessingException, UnsupportedEncodingException {

        return ResponseBaseDto.serviceError(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception e) throws JsonProcessingException, UnsupportedEncodingException {
        TelegramSender.ExceptionMessage exceptionMessage = getExceptionMessage(request, e);
        this.telegramSender.sendAsync(exceptionMessage.toString());
        log.debug("handleException: {}", exceptionMessage.toLog());
        return ResponseBaseDto.internalServerError(e.getMessage());
    }

    private TelegramSender.ExceptionMessage getExceptionMessage(final HttpServletRequest request, final Exception e) throws UnsupportedEncodingException {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));

        return TelegramSender.ExceptionMessage.builder()
                .id(this.storage.getRequestId())
                .profiles(this.environment.getActiveProfiles())
                .method(request.getMethod())
                .path(request.getContextPath() + request.getServletPath())
                .query(request.getQueryString() == null ? null : URLEncoder.encode(request.getQueryString(), StandardCharsets.UTF_8.name()))
                .token(this.storage.getToken())
                .exTrace(errors.toString().substring(0, 2000))
                .build();
    }


}