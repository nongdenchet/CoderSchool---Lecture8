package apidez.com.week8.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import apidez.com.week8.api.UserApi;
import apidez.com.week8.dependency.ActivityScope;
import apidez.com.week8.utils.ValidateUtils;
import apidez.com.week8.utils.view.TextChange;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by nongdenchet on 11/22/16.
 */

@ActivityScope
public class RegisterViewModel {
    private UserApi userApi;
    private String email = "";
    private String password = "";
    private String confirm = "";
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableField<String> confirmError = new ObservableField<>();
    public ObservableBoolean registerBtnState = new ObservableBoolean(false);
    private PublishSubject<String> message = PublishSubject.create();

    @Inject
    public RegisterViewModel(@NonNull UserApi userApi) {
        this.userApi = userApi;
    }

    public Observable<String> message() {
        return message.asObservable();
    }

    public TextChange emailChange = value -> {
        email = value;
        validateEmail();
    };

    public TextChange passwordChange = value -> {
        password = value;
        validatePassword();
    };

    public TextChange confirmChange = value -> {
        confirm = value;
        validatePassword();
    };

    private void validatePassword() {
        passwordError.set(null);
        confirmError.set(null);
        if (!ValidateUtils.validatePassword(password)) {
            passwordError.set("Password is too short");
        } else if (!confirm.equals(password)) {
            confirmError.set("Password and confirm not match");
        }
        updateBtnState();
    }

    private void validateEmail() {
        emailError.set(null);
        if (!ValidateUtils.validateEmail(email)) {
            emailError.set("Invalid email");
        }
        updateBtnState();
    }

    private void updateBtnState() {
        registerBtnState.set(!hasEmptyData() && !hasError());
    }

    private boolean hasEmptyData() {
        return email.equals("")
                || password.equals("")
                || confirm.equals("");
    }

    private boolean hasError() {
        return emailError.get() != null
                || passwordError.get() != null
                || confirmError.get() != null;
    }

    public Observable<String> register() {
        return Observable.just(registerBtnState.get())
                .filter(btnState -> btnState)
                .flatMap((value) -> userApi.register(email, password))
                .doOnNext(value -> message.onNext(value))
                .doOnError(throwable -> message.onNext(throwable.getMessage()));
    }
}
