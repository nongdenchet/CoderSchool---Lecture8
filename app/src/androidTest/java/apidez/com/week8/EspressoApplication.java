package apidez.com.week8;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;

import apidez.com.week8.data.api.UserApi;
import apidez.com.week8.data.repo.UserRepo;
import apidez.com.week8.dependency.component.AppComponent;
import apidez.com.week8.dependency.component.DaggerAppComponent;
import apidez.com.week8.dependency.module.RepoModule;

import static org.mockito.Mockito.mock;

/**
 * Created by nongdenchet on 11/26/16.
 */

public class EspressoApplication extends MyApplication {
    public UserRepo userRepo = mock(UserRepo.class);

    public static <T extends AppCompatActivity> EspressoApplication get(ActivityTestRule<T> activityTestRule) {
        return (EspressoApplication) activityTestRule.getActivity().getApplication();
    }

    @Override
    public AppComponent component() {
        return DaggerAppComponent.builder()
                .repoModule(repoModule())
                .build();
    }

    private RepoModule repoModule() {
        return new RepoModule() {
            @Override
            public UserRepo provideUserApi(UserApi userApi) {
                return userRepo;
            }
        };
    }
}
