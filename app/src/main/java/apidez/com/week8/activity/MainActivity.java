package apidez.com.week8.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import apidez.com.week8.R;
import apidez.com.week8.viewmodel.ViewModel;
import apidez.com.week8.databinding.ActivityMainBinding;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private ViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModel();
        binding.setViewModel(viewModel);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick() {
        viewModel.increase();
    }
}
