package com.weproud.exception;

/**
 * Logan. k
 */
public class UserExistException extends ServiceException {
    public UserExistException(final String code, final String message) {
        super(code, message);
    }
}
