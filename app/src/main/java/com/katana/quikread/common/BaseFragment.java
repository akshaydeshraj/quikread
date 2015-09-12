package com.katana.quikread.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.katana.quikread.App;
import com.katana.quikread.components.AppComponent;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Sep-15
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.get(getActivity()).component());
    }

    protected abstract void setupComponent(AppComponent appComponent);
}
