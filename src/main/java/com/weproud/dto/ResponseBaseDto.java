package com.weproud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * Logan. k
 */

@ToString
@Getter
@Builder
public class ResponseBaseDto<T> {
    private HttpStatus status;
    private String message;
    private T response;
    private LocalDateTime responseAt;

    public static <T> ResponseBaseDto to(final HttpStatus status, final String message, final T t) {
        return ResponseBaseDto.builder()
                .status(status)
                .message(message)
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
    }

    public static <T> ResponseEntity response(final HttpStatus status, final String message, final T t) {
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(status)
                .message(message)
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, status);
    }

    public static <T> ResponseEntity created(final String message, final T t) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message(message)
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    public static <T> ResponseEntity created(final T t) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message("")
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    public static <T> ResponseEntity ok(final String message, final T t) {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message(message)
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    public static <T> ResponseEntity ok(final T t) {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message("")
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    public static <T> ResponseEntity ok() {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message("")
                .response(null)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    public static ResponseEntity serviceError(final String message) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message(message)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }

    public static ResponseEntity internalServerError(final String message) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseBaseDto dto = ResponseBaseDto.builder()
                .status(httpStatus)
                .message(message)
                .responseAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, httpStatus);
    }
}
