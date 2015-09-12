package com.katana.quikread.modules;

import com.katana.quikread.App;

import dagger.Module;
import dagger.Provides;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App provideApp(){
        return app;
    }
}
