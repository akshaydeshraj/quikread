package com.katana.quikread.components;

import com.katana.quikread.App;
import com.katana.quikread.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    void inject(App application);

    App getApp();
}
