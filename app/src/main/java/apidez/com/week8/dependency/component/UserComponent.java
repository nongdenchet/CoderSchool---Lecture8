package apidez.com.week8.dependency.component;

import apidez.com.week8.activity.RegisterActivity;
import apidez.com.week8.dependency.ActivityScope;
import apidez.com.week8.dependency.module.UserModule;
import dagger.Subcomponent;

/**
 * Created by nongdenchet on 11/25/16.
 */

@ActivityScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
    void inject(RegisterActivity registerActivity);
}
