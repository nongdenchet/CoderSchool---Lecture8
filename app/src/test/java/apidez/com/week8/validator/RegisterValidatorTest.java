package apidez.com.week8.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nongdenchet on 11/26/16.
 */
public class RegisterValidatorTest {
    private RegisterValidator validator;

    @Before
    public void init() {
        validator = new RegisterValidator();
    }

    @Test
    public void validateInvalidEmail() throws Exception {
        assertFalse(validator.validateEmail("123"));
    }

    @Test
    public void validateInvalidEmail2() throws Exception {
        assertFalse(validator.validateEmail("123@@@@@"));
    }

    @Test
    public void validateNullEmail() throws Exception {
        assertFalse(validator.validateEmail(null));
    }

    @Test
    public void validateEmail() throws Exception {
        assertTrue(validator.validateEmail("android@gm.co"));
    }

    @Test
    public void validatePassword() throws Exception {
        assertTrue(validator.validatePassword("12345678"));
    }

    @Test
    public void validateInvalidPassword() throws Exception {
        assertFalse(validator.validatePassword("123"));
    }

    @Test
    public void validateNullPassword() throws Exception {
        assertFalse(validator.validatePassword(null));
    }
}