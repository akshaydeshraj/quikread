package com.katana.quikread.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.katana.quikread.R;
import com.katana.quikread.models.QuikreadItem;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author DEEPANKAR
 * @since 12-09-2015.
 */
public class BookItemFragment extends Fragment{

    @Bind(R.id.item_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.item_toolbar)
    Toolbar toolbar;

    @Bind(R.id.item_image)
    ImageView bookImageView;

    @Bind(R.id.book_rating)
    RatingBar ratingBar;

    @Bind(R.id.book_title)
    TextView bookTitle;

    @Bind(R.id.book_genre)
    TextView bookGenre;

    @Bind(R.id.book_author)
    TextView bookAuthor;

    @Bind(R.id.book_description)
    TextView bookDescription;

    QuikreadItem quikreadItem;

    public static Fragment newInstance(QuikreadItem quikreadItem){

        Bundle args = new Bundle();//TODO : fill this bundle
        args.putSerializable("quikreadItem", quikreadItem);
        BookItemFragment bookItemFragment = new BookItemFragment();
        bookItemFragment.setArguments(args);

        return bookItemFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            quikreadItem = (QuikreadItem)getArguments().getSerializable("quikreadItem");
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

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ratingBar.setRating(Float.parseFloat(quikreadItem.getRating()));
        bookTitle.setText(quikreadItem.getTitle());
        bookGenre.setText(quikreadItem.getGenre());
        bookAuthor.setText(quikreadItem.getAuthor());
        bookDescription.setText(quikreadItem.getDescription());

        Picasso.with(getActivity()).load(Uri.parse(quikreadItem.getImageUrl())).centerCrop().into(bookImageView);
    }
}
