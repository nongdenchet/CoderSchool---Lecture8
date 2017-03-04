package apidez.com.week8.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import apidez.com.week8.data.repo.UserRepo;
import apidez.com.week8.dependency.ActivityScope;
import apidez.com.week8.utils.view.TextChange;
import apidez.com.week8.validator.RegisterValidator;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by nongdenchet on 11/22/16.
 */

@ActivityScope
public class RegisterViewModel {
    private UserRepo userRepo;
    private RegisterValidator validator;
    private String email = "";
    private String password = "";
    private String confirm = "";
    private PublishSubject<String> message = PublishSubject.create();
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableField<String> confirmError = new ObservableField<>();
    public ObservableBoolean isValid = new ObservableBoolean(false);

    @Inject
    RegisterViewModel(@NonNull UserRepo userRepo, @NonNull RegisterValidator validator) {
        this.userRepo = userRepo;
        this.validator = validator;
    }

    public Observable<String> message() {
        return message.asObservable();
    }

    public TextChange emailChange = value -> {
        email = value;
        emailError.set(validator.validateEmail(email));
        updateBtnState();
    };

    public TextChange passwordChange = value -> {
        password = value;
        passwordError.set(validator.validatePassword(password));
        updateBtnState();
    };

    public TextChange confirmChange = value -> {
        confirm = value;
        confirmError.set(validator.validateConfirm(password, confirm));
        updateBtnState();
    };

    private void updateBtnState() {
        isValid.set(!hasEmptyData() && !hasError());
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
        return Observable.just(isValid.get())
                .filter(btnState -> btnState)
                .flatMap((value) -> userRepo.register(email, password))
                .doOnNext(value -> message.onNext(value))
                .doOnError(throwable -> message.onNext(throwable.getMessage()));
    }
}
