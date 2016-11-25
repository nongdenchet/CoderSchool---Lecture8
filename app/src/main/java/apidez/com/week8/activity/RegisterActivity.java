package apidez.com.week8.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import apidez.com.week8.MyApplication;
import apidez.com.week8.R;
import apidez.com.week8.databinding.ActivityRegisterBinding;
import apidez.com.week8.dependency.module.UserModule;
import apidez.com.week8.viewmodel.RegisterViewModel;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Inject
    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).component()
                .plus(new UserModule())
                .inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setViewModel(viewModel);
        ButterKnife.bind(this);
        bindViewModel();
    }

    private void bindViewModel() {
        viewModel.toast
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    Toast.makeText(RegisterActivity.this, value, Toast.LENGTH_SHORT).show();
                });
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClick() {
        viewModel.register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {}, throwable -> {});
    }
}
