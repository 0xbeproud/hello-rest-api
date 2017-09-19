package com.weproud.exception;

import lombok.Getter;

/**
 * @author Logan.k
 */
@Getter
public class ServiceException extends RuntimeException {
    protected String code;

    public ServiceException(final String code, final String message){
        super(message);
        this.code = code;
    }
}
