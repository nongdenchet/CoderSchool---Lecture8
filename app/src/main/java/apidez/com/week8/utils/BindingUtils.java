package apidez.com.week8.utils;

import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import apidez.com.week8.utils.view.TextChange;
import apidez.com.week8.utils.view.TextChangeAdapter;

/**
 * Created by nongdenchet on 11/22/16.
 */

public class BindingUtils {

    @BindingAdapter("textChange")
    public static void textChange(final EditText editText, final TextChange textChange) {
        editText.addTextChangedListener(new TextChangeAdapter() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textChange.onChange(charSequence.toString());
            }
        });
    }

    public static void bindEnable(View view, final ObservableBoolean observableBoolean) {
        observableBoolean.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                view.setEnabled(observableBoolean.get());
            }
        });
    }

    public static void bindError(TextInputLayout inputLayout, final ObservableField<String> error) {
        error.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                inputLayout.setError(error.get());
            }
        });
    }
}
