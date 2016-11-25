package apidez.com.week8.dependency.module;

import android.content.Context;

import javax.inject.Singleton;

import apidez.com.week8.utils.Api;
import apidez.com.week8.utils.Data;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nongdenchet on 11/24/16.
 */

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public Api provideApi() {
        return new Api();
    }

    @Provides
    @Singleton
    public Data provideData(Api api) {
        return new Data(api);
    }
}
