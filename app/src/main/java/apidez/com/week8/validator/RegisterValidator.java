package apidez.com.week8.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nongdenchet on 11/26/16.
 */

public class RegisterValidator {
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public String validateEmail(String email) {
        return validEmail(email) ? null : "Invalid email";
    }

    private boolean validEmail(String emailStr) {
        if (emailStr == null) return false;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public String validatePassword(String password) {
        return validPassword(password) ? null : "Password is too short";
    }

    private boolean validPassword(String password) {
        return password != null && password.length() >= 8;
    }

    public String validateConfirm(String password, String confirm) {
        return validConfirm(password, confirm) ? null : "Password and confirm not match";
    }

    private boolean validConfirm(String password, String confirm) {
        return confirm != null && confirm.equals(password);
    }
}
