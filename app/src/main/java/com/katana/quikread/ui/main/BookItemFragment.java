package com.katana.quikread.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.katana.quikread.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author DEEPANKAR
 * @since 12-09-2015.
 */
public class BookItemFragment extends Fragment{

    @Bind(R.id.textView)
    TextView textView;

    @Bind(R.id.item_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.item_toolbar)
    Toolbar toolbar;

    @Bind(R.id.item_image)
    ImageView bookImageView;

    @Bind(R.id.item_fab)
    FloatingActionButton fab;

    int position;

    public static Fragment newInstance(Bundle args){

        BookItemFragment bookItemFragment = new BookItemFragment();
        bookItemFragment.setArguments(args);

        return bookItemFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            position = getArguments().getInt("position");
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
        ButterKnife.bind(this, view);


        textView.setText(""+position);
    }
}
