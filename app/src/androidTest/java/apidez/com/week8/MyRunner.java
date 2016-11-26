package apidez.com.week8;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by nongdenchet on 11/26/16.
 */

public class MyRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return super.newApplication(cl, EspressoApplication.class.getName(), context);
    }
}
