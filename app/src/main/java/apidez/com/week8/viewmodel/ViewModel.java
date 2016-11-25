package apidez.com.week8.viewmodel;

import android.databinding.ObservableInt;

/**
 * Created by nongdenchet on 11/22/16.
 */

public class ViewModel {
    public ObservableInt value = new ObservableInt(0);

    public void increase() {
        value.set(value.get() + 1);
    }
}
