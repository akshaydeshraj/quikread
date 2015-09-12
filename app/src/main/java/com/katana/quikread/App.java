package com.katana.quikread;

import android.app.Application;
import android.content.Context;

import com.katana.quikread.components.AppComponent;
import com.katana.quikread.components.DaggerAppComponent;
import com.katana.quikread.modules.AppModule;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    public void setupGraph(){
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }
}
