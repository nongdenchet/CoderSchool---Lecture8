package apidez.com.week8.data.repo;

import rx.Observable;

/**
 * Created by nongdenchet on 11/25/16.
 */

public interface UserRepo {
    Observable<String> register(final String email, String password);
}
