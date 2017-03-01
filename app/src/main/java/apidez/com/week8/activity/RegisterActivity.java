package apidez.com.week8.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import apidez.com.week8.R;
import apidez.com.week8.dependency.module.UserModule;
import apidez.com.week8.utils.BindingUtils;
import apidez.com.week8.viewmodel.RegisterViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@SuppressWarnings("FieldCanBeLocal")
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPassword) EditText edtPassword;
    @BindView(R.id.edtConfirm) EditText edtConfirm;
    @BindView(R.id.inputEmail) TextInputLayout inputEmail;
    @BindView(R.id.inputConfirm) TextInputLayout inputConfirm;
    @BindView(R.id.inputPassword) TextInputLayout inputPassword;
    @BindView(R.id.btnRegister) Button btnRegister;
    @Inject RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().plus(new UserModule()).inject(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindViewModel() {
        viewModel.message()
                .takeUntil(stopEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showMessage);
        BindingUtils.bindEnable(btnRegister, viewModel.registerBtnState);
        BindingUtils.textChange(edtEmail, viewModel.emailChange);
        BindingUtils.textChange(edtPassword, viewModel.passwordChange);
        BindingUtils.textChange(edtConfirm, viewModel.confirmChange);
        BindingUtils.bindError(inputEmail, viewModel.emailError);
        BindingUtils.bindError(inputPassword, viewModel.passwordError);
        BindingUtils.bindError(inputConfirm, viewModel.confirmError);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClick() {
        viewModel.register()
                .takeUntil(stopEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {}, throwable -> {});
    }

    private void showMessage(String value) {
        new AlertDialog.Builder(this)
                .setMessage(value)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
