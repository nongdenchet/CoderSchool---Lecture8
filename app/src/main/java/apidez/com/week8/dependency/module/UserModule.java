package apidez.com.week8.dependency.module;

import apidez.com.week8.validator.RegisterValidator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nongdenchet on 11/25/16.
 */

@Module
public class UserModule {

    @Provides
    public RegisterValidator provideRegisterValidator() {
        return new RegisterValidator();
    }
}
