package apidez.com.week8.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import javax.inject.Inject;

import apidez.com.week8.R;
import apidez.com.week8.databinding.ActivityRegisterBinding;
import apidez.com.week8.dependency.component.UserComponent;
import apidez.com.week8.dependency.module.UserModule;
import apidez.com.week8.viewmodel.RegisterViewModel;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding binding;
    private UserComponent userComponent;

    @Inject
    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userComponent = getAppComponent().plus(new UserModule());
        userComponent.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setViewModel(viewModel);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindViewModel() {
        viewModel.message()
                .takeUntil(stopEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showMessage);
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
