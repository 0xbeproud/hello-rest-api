package com.weproud.base;

import com.weproud.exception.ServiceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Logan.k
 */
public class ServiceExceptionMatcherTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = ServiceException.class)
    public void expected_exception_by_annotation() throws Exception {
        throw new ServiceException("errorCode", "test");
    }

    @Test
    public void expected_exception_by_rule() throws Exception {
        expectedException.expect(ServiceException.class);
        throw new ServiceException("errorCode", "test");
    }

    @Test
    public void expected_exception_message_by_rule() throws Exception {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Expected NullPointerException with rule");
        throw new NullPointerException("Expected NullPointerException with rule");
    }

    @Test
    public void expected_exception_code_by_rule() throws Exception {
        expectedException.expect(ServiceExceptionMatcher.expectedCode("errorCode"));
        throw new ServiceException("errorCode", "xxxxxxxxxxxxx");
    }

    @Test
    public void expected_exception_exception_and_code_by_rule() throws Exception {
        expectedException.expect(ServiceException.class);
        expectedException.expect(ServiceExceptionMatcher.expectedCode("errorCode"));
        throw new ServiceException("errorCode", "xxxxxxxxxxxxx");
    }
}
