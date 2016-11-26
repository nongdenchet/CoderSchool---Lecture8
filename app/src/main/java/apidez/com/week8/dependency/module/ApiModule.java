package apidez.com.week8.dependency.module;

import apidez.com.week8.api.FakeUserApiImpl;
import apidez.com.week8.api.UserApi;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nongdenchet on 11/24/16.
 */

@Module
public class ApiModule {

    @Provides
    public UserApi provideUserApi() {
        return new FakeUserApiImpl();
    }
}
