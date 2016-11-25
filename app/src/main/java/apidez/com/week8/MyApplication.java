package apidez.com.week8;

import android.app.Application;

import apidez.com.week8.dependency.component.AppComponent;
import apidez.com.week8.dependency.component.DaggerAppComponent;
import apidez.com.week8.dependency.module.AppModule;

/**
 * Created by nongdenchet on 11/24/16.
 */

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}
