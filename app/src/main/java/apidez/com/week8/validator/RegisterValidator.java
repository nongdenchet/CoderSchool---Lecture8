package apidez.com.week8.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nongdenchet on 11/26/16.
 */

public class RegisterValidator {
    public final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String emailStr) {
        if (emailStr == null) return false;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public boolean validatePassword(String password) {
        return password != null && password.length() >= 8;
    }
}
