package apidez.com.week8.api;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by nongdenchet on 11/25/16.
 */

public class FakeUserApiImpl implements UserApi {

    public Observable<String> register(final String email, String password) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (email.equals("fpt@gmail.com")) {
                    subscriber.onError(new Throwable("Email has been used"));
                } else {
                    subscriber.onNext("Success");
                    subscriber.onCompleted();
                }
            }
        });
    }
}
