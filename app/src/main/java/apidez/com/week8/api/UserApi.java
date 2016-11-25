package apidez.com.week8.api;

import rx.Observable;

/**
 * Created by nongdenchet on 11/25/16.
 */

public interface UserApi {
    Observable<String> register(final String email, String password);
}
