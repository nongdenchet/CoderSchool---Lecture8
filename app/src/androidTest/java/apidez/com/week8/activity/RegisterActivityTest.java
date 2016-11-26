package apidez.com.week8.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.LinearLayout;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import apidez.com.week8.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static apidez.com.week8.utils.EspressoUtils.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void emailError() {
        edtEmail().perform(typeText("android"), closeSoftKeyboard());
        checkError("Invalid email");
        edtEmail().perform(replaceText("android@gmail.com"), closeSoftKeyboard());
        checkNoError("Invalid email");
    }

    @Test
    public void passwordError() {
        edtPassword().perform(typeText("123"), closeSoftKeyboard());
        checkError("Password is too short");
        edtPassword().perform(replaceText("12345678"), closeSoftKeyboard());
        checkNoError("Password is too short");
    }

    @Test
    public void confirmError() {
        edtPassword().perform(replaceText("12345678"), closeSoftKeyboard());
        edtConfirm().perform(typeText("123"), closeSoftKeyboard());
        checkError("Password and confirm not match");
        edtConfirm().perform(replaceText("12345678"), closeSoftKeyboard());
        checkNoError("Password and confirm not match");
    }

    @Test
    public void registerSuccess() {
        edtEmail().perform(typeText("android@gmail.com"), closeSoftKeyboard());
        edtPassword().perform(replaceText("12345678"), closeSoftKeyboard());
        edtConfirm().perform(replaceText("12345678"), closeSoftKeyboard());
        registerBtn().perform(click());
        checkMessage("Success");
    }

    @Test
    public void registerFail() {
        edtEmail().perform(typeText("fpt@gmail.com"), closeSoftKeyboard());
        edtPassword().perform(replaceText("12345678"), closeSoftKeyboard());
        edtConfirm().perform(replaceText("12345678"), closeSoftKeyboard());
        registerBtn().perform(click());
        checkMessage("Email has been used");
    }

    private void checkMessage(String message) {
        onView(allOf(withId(android.R.id.message), withText(message),
                childAtPosition(childAtPosition(withId(R.id.scrollView), 0), 0), isDisplayed()))
                .check(matches(withText(message)));
    }

    private void checkError(String message) {
        onView(allOf(withText(message), childAtPosition(childAtPosition(
                IsInstanceOf.instanceOf(LinearLayout.class), 1), 0), isDisplayed()));
    }

    private void checkNoError(String message) {
        onView(allOf(withText(message), childAtPosition(childAtPosition(
                IsInstanceOf.instanceOf(LinearLayout.class), 1), 0), not(isDisplayed())));
    }

    private ViewInteraction registerBtn() {
        return onView(allOf(withId(R.id.btnRegister), withText("Register"),
                withParent(allOf(withId(R.id.activity_register),
                        withParent(withId(android.R.id.content)))),
                isDisplayed()));
    }

    private ViewInteraction edtEmail() {
        return onView(allOf(withId(R.id.edtEmail), isDisplayed()));
    }

    private ViewInteraction edtPassword() {
        return onView(allOf(withId(R.id.edtPassword), isDisplayed()));
    }

    private ViewInteraction edtConfirm() {
        return onView(allOf(withId(R.id.edtConfirm), isDisplayed()));
    }
}
