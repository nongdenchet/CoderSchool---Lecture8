package apidez.com.week8.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import apidez.com.week8.MyApplication;
import apidez.com.week8.dependency.component.AppComponent;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by nongdenchet on 11/26/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private AppComponent appComponent;
    private static final int START = 0;
    private static final int STOP = 1;
    private PublishSubject<Integer> stopEvent = PublishSubject.create();
    private PublishSubject<Integer> startEvent = PublishSubject.create();

    public Observable<Integer> stopEvent() {
        return stopEvent.asObservable();
    }

    public Observable<Integer> startEvent() {
        return startEvent.asObservable();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = ((MyApplication) getApplication()).component();
    }

    protected AppComponent getAppComponent() {
        return appComponent;
    }

    protected abstract void bindViewModel();

    @Override
    protected void onStart() {
        super.onStart();
        startEvent.onNext(START);
        bindViewModel();
    }

    @Override
    protected void onStop() {
        stopEvent.onNext(STOP);
        super.onStop();
    }
}
