package apidez.com.week8.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nongdenchet on 11/22/16.
 */

public class ValidateUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        return password != null && password.length() >= 8;
    }
}
