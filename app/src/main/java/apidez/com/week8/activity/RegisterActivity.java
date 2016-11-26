package apidez.com.week8.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

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
        viewModel.toast()
                .takeUntil(stopEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((value) -> showMessage(value));
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
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }
}
