package com.weproud.dto;

import com.weproud.exception.ServiceException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * Logan. k
 */

@Getter
@Builder
public class ResponseBaseDto<T> {

    private String code;
    private String message;
    private T response;
    private LocalDateTime responseAt;

    public static <T> ResponseBaseDto build(final ServiceResponseCode serviceResponseCode, final T t) {
        return build(serviceResponseCode.getCode(), serviceResponseCode.getMessage(), t);
    }

    public static <T> ResponseBaseDto build(final String code, final String message, final T t) {
        return ResponseBaseDto.builder()
                .code(code)
                .message(message)
                .response(t)
                .responseAt(LocalDateTime.now())
                .build();
    }

    public static <T> ResponseEntity created(final T t) {
        return new ResponseEntity<>(ResponseBaseDto.build(ServiceResponseCode.OK, t), HttpStatus.CREATED);
    }

    public static ResponseEntity created() {
        return created(null);
    }

    public static <T> ResponseEntity ok(final T t) {
        return new ResponseEntity<>(ResponseBaseDto.build(ServiceResponseCode.OK, t), HttpStatus.OK);
    }

    public static ResponseEntity ok() {
        return new ResponseEntity<>(ResponseBaseDto.build(ServiceResponseCode.OK, null), HttpStatus.OK);
    }

    public static ResponseEntity serviceError(final ServiceException e) {
        return new ResponseEntity<>(ResponseBaseDto.build(e.getCode(), e.getMessage(), null), HttpStatus.OK);
    }

    public static <T> ResponseEntity internalServerError(final T t) {
        return new ResponseEntity<>(ResponseBaseDto.build(ServiceResponseCode.INTERNAL_SERVER_ERROR, t), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity badRequest(final T t) {
        return new ResponseEntity<>(ResponseBaseDto.build(ServiceResponseCode.BAD_REQUEST, t), HttpStatus.BAD_REQUEST);
    }

    @Getter
    public enum ServiceResponseCode {
        OK("OK", "정상 처리 되었습니다."),
        BAD_REQUEST("BAD_REQUEST", "잘못된 요청입니다."),
        INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생하였습니다.");

        private String code;
        private String message;

        ServiceResponseCode(final String code, final String message) {
            this.code = code;
            this.message = message;
        }
    }
}

