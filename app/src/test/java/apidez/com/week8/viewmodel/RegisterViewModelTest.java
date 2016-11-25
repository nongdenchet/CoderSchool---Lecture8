package apidez.com.week8.viewmodel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import apidez.com.week8.api.UserApi;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by nongdenchet on 11/25/16.
 */
public class RegisterViewModelTest {
    private RegisterViewModel viewModel;
    private TestSubscriber<String> testSubscriber = TestSubscriber.create();

    @Mock
    UserApi userApi;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        viewModel = new RegisterViewModel(userApi);
    }

    @Test
    public void emailError_invalidEmail_showInvalidEmail() {
        viewModel.emailChange.onChange("good game");
        assertEquals("Invalid email", viewModel.emailError.get());
    }

    @Test
    public void emailError_emptyEmail_showInvalidEmail() {
        viewModel.emailChange.onChange("");
        assertEquals("Invalid email", viewModel.emailError.get());
    }

    @Test
    public void emailError_validEmail_notShowError() {
        viewModel.emailChange.onChange("fpt@gmail.com");
        assertNull(viewModel.emailError.get());
    }

    @Test
    public void emailError_validEmailFromInvalid_notShowError() {
        viewModel.emailChange.onChange("gg");
        viewModel.emailChange.onChange("fpt@gmail.com");
        assertNull(viewModel.emailError.get());
    }

    @Test
    public void emailError_init_showInvalidEmail() {
        assertNull(viewModel.emailError.get());
    }

    @Test
    public void passwordError_shortPassword_showShortPassword() {
        viewModel.passwordChange.onChange("abc");
        assertEquals("Password is too short", viewModel.passwordError.get());
    }

    @Test
    public void passwordError_init_notShowError() {
        assertNull(viewModel.passwordError.get());
    }

    @Test
    public void passwordError_shortPasswordAndConfirm_showShortPassword() {
        viewModel.passwordChange.onChange("abc");
        viewModel.confirmChange.onChange("ab123456789c");
        assertEquals("Password is too short", viewModel.passwordError.get());
    }

    @Test
    public void confirmError_init_notShowError() {
        assertNull(viewModel.confirmError.get());
    }

    @Test
    public void confirmError_notMatch_showError() {
        viewModel.passwordChange.onChange("12345678");
        assertEquals("Password and confirm not match", viewModel.confirmError.get());
    }

    @Test
    public void btnState_init_disable() {
        assertFalse(viewModel.registerBtnState.get());
    }

    @Test
    public void btnState_emailInvalid_disable() {
        viewModel.registerBtnState.set(true);
        viewModel.emailChange.onChange("invalid");
        assertFalse(viewModel.registerBtnState.get());
    }

    @Test
    public void btnState_passwordInvalid_disable() {
        viewModel.registerBtnState.set(true);
        viewModel.passwordChange.onChange("abc");
        assertFalse(viewModel.registerBtnState.get());
    }

    @Test
    public void btnState_passwordAndConfirmEmpty_disable() {
        viewModel.emailChange.onChange("fpt@gmail.com");
        assertFalse(viewModel.registerBtnState.get());
    }

    @Test
    public void confirmError_confirmMatch_showNoError() {
        viewModel.passwordChange.onChange("12345678");
        viewModel.confirmChange.onChange("12345678");
        assertNull(viewModel.confirmError.get());
    }

    @Test
    public void btnState_confirmNotMatch_disable() {
        viewModel.passwordChange.onChange("12345678");
        viewModel.confirmChange.onChange("abc");
        assertFalse(viewModel.registerBtnState.get());
    }

    @Test
    public void registerEvent_noValidation_notEffect() {
        viewModel.register().toBlocking().subscribe(testSubscriber);
        testSubscriber.assertNoValues();
    }

    @Test
    public void toast_confirmNotMatch_noEffect() {
        viewModel.emailChange.onChange("email@gm.co");
        viewModel.passwordChange.onChange("12345678");
        viewModel.toast.subscribe(testSubscriber);
        when(userApi.register("email@gm.co", "12345678"))
                .thenReturn(Observable.just("Success"));
        viewModel.register().toBlocking().subscribe();
        testSubscriber.assertNoValues();
    }

    @Test
    public void toast_newEmail_success() {
        viewModel.emailChange.onChange("email@gm.co");
        viewModel.passwordChange.onChange("12345678");
        viewModel.confirmChange.onChange("12345678");
        viewModel.toast.subscribe(testSubscriber);
        when(userApi.register("email@gm.co", "12345678"))
                .thenReturn(Observable.just("Success"));
        viewModel.register().toBlocking().subscribe();
        testSubscriber.assertValue("Success");
    }

    @Test
    public void toast_existedEmail_emailUsed() {
        viewModel.emailChange.onChange("email@gm.co");
        viewModel.passwordChange.onChange("12345678");
        viewModel.confirmChange.onChange("12345678");
        viewModel.toast.subscribe(testSubscriber);
        when(userApi.register("email@gm.co", "12345678"))
                .thenReturn(Observable.error(new Throwable("Error")));
        viewModel.register().toBlocking().subscribe(value -> {}, throwable -> {});
        testSubscriber.assertValue("Error");
    }
}
