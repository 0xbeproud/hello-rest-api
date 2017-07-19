package com.weproud.exception;

/**
 * Logan. k
 */
public class InvalidArgumentException extends ServiceException {
    public InvalidArgumentException(final String code, final String message) {
        super(code, message);
    }

    public InvalidArgumentException(final String arg){
        super("InvalidArgumentException", String.format("%s 는 필수 파라미터입니다.", arg));
    }
}
