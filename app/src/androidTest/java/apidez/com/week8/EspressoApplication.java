package apidez.com.week8;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;

import apidez.com.week8.api.UserApi;
import apidez.com.week8.dependency.component.AppComponent;
import apidez.com.week8.dependency.component.DaggerAppComponent;
import apidez.com.week8.dependency.module.ApiModule;

import static org.mockito.Mockito.mock;

/**
 * Created by nongdenchet on 11/26/16.
 */

public class EspressoApplication extends MyApplication {
    public UserApi userApi = mock(UserApi.class);

    public static <T extends AppCompatActivity> EspressoApplication get(ActivityTestRule<T> activityTestRule) {
        return (EspressoApplication) activityTestRule.getActivity().getApplication();
    }

    @Override
    public AppComponent component() {
        return DaggerAppComponent.builder()
                .apiModule(apiModule())
                .build();
    }

    private ApiModule apiModule() {
        return new ApiModule() {
            @Override
            public UserApi provideUserApi() {
                return userApi;
            }
        };
    }
}
