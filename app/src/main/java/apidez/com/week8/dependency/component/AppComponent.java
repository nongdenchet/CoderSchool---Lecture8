package apidez.com.week8.dependency.component;

import javax.inject.Singleton;

import apidez.com.week8.dependency.module.RepoModule;
import apidez.com.week8.dependency.module.AppModule;
import apidez.com.week8.dependency.module.UserModule;
import dagger.Component;

/**
 * Created by nongdenchet on 11/24/16.
 */

@Singleton
@Component(modules = {AppModule.class, RepoModule.class})
public interface AppComponent {
    UserComponent plus(UserModule userModule);
}
