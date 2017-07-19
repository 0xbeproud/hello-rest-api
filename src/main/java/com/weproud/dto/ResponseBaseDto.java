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
    private Status status;
    private T response;
    private LocalDateTime responseAt;

    public static <T> ResponseBaseDto build(final Status status, final T t) {
        return ResponseBaseDto.builder()
                .status(status)
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
    }

    public static <T> ResponseEntity created(final T t) {
        return new ResponseEntity<>(ResponseBaseDto.build(Status.OK, t), HttpStatus.CREATED);
    }

    public static <T> ResponseEntity ok(final T t) {
        return ResponseEntity.ok(ResponseBaseDto.build(Status.OK, t));
    }

    public static ResponseEntity ok() {
        return ResponseEntity.ok(ResponseBaseDto.build(Status.OK, null));
    }

    public static <T> ResponseEntity serviceError(final T t) {
        return ResponseEntity.badRequest().body(ResponseBaseDto.build(Status.FAIL, t));
    }

    public static <T> ResponseEntity internalServerError(final T t) {
        return new ResponseEntity<>(ResponseBaseDto.build(Status.FAIL, t), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity badRequest(final T t) {
        return ResponseEntity.badRequest().body(ResponseBaseDto.build(Status.FAIL, t));
    }

    public enum Status {
        OK, FAIL
    }
}
