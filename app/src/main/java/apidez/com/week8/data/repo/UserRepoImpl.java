package apidez.com.week8.data.repo;

import android.support.annotation.NonNull;

import apidez.com.week8.data.api.UserApi;
import rx.Observable;

/**
 * Created by nongdenchet on 11/27/16.
 */

public class UserRepoImpl implements UserRepo {
    private UserApi userApi;

    public UserRepoImpl(@NonNull  UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public Observable<String> register(String email, String password) {
        return userApi.register(email, password);
    }
}
