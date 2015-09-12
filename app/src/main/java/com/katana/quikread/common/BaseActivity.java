package com.katana.quikread.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.katana.quikread.App;
import com.katana.quikread.components.AppComponent;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.get(this).component());
    }

    protected abstract void setupComponent(AppComponent component);
}
