package com.weproud.base;

import com.weproud.exception.ServiceException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Logan.k
 */
public class ServiceExceptionMatcher extends TypeSafeMatcher<ServiceException> {

    private final String expectedErrorCode;
    private String foundErrorCode;

    private ServiceExceptionMatcher(String expectedErrorCode) {
        this.expectedErrorCode = expectedErrorCode;
    }

    public static ServiceExceptionMatcher expectedCode(String expectedErrorCode) {
        return new ServiceExceptionMatcher(expectedErrorCode);
    }

    @Override
    protected boolean matchesSafely(final ServiceException exception) {
        foundErrorCode = exception.getCode();
        return foundErrorCode.equalsIgnoreCase(expectedErrorCode);
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(foundErrorCode)
                .appendText(" was not found instead of ")
                .appendValue(expectedErrorCode);
    }
}
