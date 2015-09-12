package com.katana.quikread.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katana.quikread.R;

/**
 * @author DEEPANKAR
 * @since 12-09-2015.
 */
public class BookItemFragment extends Fragment{


    public static Fragment newInstance(Bundle args){

        BookItemFragment bookItemFragment = new BookItemFragment();
        bookItemFragment.setArguments(args);

        return bookItemFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            // TODO: retrieve the data
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
