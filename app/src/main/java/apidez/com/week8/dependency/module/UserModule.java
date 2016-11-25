package apidez.com.week8.dependency.module;

import apidez.com.week8.api.UserApi;
import apidez.com.week8.api.FakeUserApiImpl;
import apidez.com.week8.dependency.ActivityScope;
import apidez.com.week8.viewmodel.RegisterViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nongdenchet on 11/25/16.
 */

@Module
public class UserModule {

    @Provides
    public UserApi provideUserApi() {
        return new FakeUserApiImpl();
    }

    @Provides
    @ActivityScope
    public RegisterViewModel provideRegisterViewModel(UserApi userApi) {
        return new RegisterViewModel(userApi);
    }
}
