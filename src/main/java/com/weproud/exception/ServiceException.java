package com.weproud.exception;

/**
 * @author Logan.k
 */
public class ServiceException extends RuntimeException {
    protected String code;


    public ServiceException(final String code, final String message){
        super(message);
        this.code = code;
    }
}
