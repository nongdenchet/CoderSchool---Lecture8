package apidez.com.week8.dependency.module;

import apidez.com.week8.data.api.UserApi;
import apidez.com.week8.data.repo.UserRepo;
import apidez.com.week8.data.repo.UserRepoImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nongdenchet on 11/24/16.
 */

@Module
public class RepoModule {

    @Provides
    public UserRepo provideUserApi(UserApi userApi) {
        return new UserRepoImpl(userApi);
    }
}
