package com.katana.quikread.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.katana.quikread.ActivityScope;
import com.katana.quikread.R;
import com.katana.quikread.common.BaseActivity;
import com.katana.quikread.components.AppComponent;
import com.katana.quikread.models.QuikrItem;
import com.katana.quikread.models.QuikreadItem;
import com.katana.quikread.rest.OnRequestFinishedListener;
import com.katana.quikread.rest.RestDataSource;
import com.katana.quikread.rest.models.BookSearchResponse;
import com.katana.quikread.rest.models.BooksByLocationResponse;
import com.katana.quikread.ui.utils.DepthPageTransformer;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Component;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity implements OnRequestFinishedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @ActivityScope
    @Component(
            dependencies = AppComponent.class
    )
    public interface MainComponent {
        void inject(MainActivity activity);
    }

    @Bind(R.id.main_viewpager)
    ViewPager viewPager;

    @Bind(R.id.main_fab)
    FloatingActionButton fab;

    @Inject
    RestDataSource restDataSource;

    ViewPagerAdapter viewPagerAdapter;
    ArrayList<QuikreadItem> quikreadItemArrayList = new ArrayList<>();

    private int count = 0;
    private int currentFragmentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        restDataSource.setOnRequestFinishedListener(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setPageTransformer(false, new DepthPageTransformer());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentFragmentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BookDetailsActivity.class);
                intent.putExtra("quickread",quikreadItemArrayList.get(currentFragmentPosition));
                startActivity(intent);
            }
        });

        restDataSource.fetchBooksByLocation("Delhi"); //TODO: remove hardcoded location

    }

    @Override
    protected void setupComponent(AppComponent component) {

        DaggerMainActivity_MainComponent.builder()
                .appComponent(component)
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(Object response, Response retrofitResponse) {

        Log.v(TAG, response.toString());

        if(response instanceof BooksByLocationResponse){
            Log.i(TAG, String.valueOf(((BooksByLocationResponse)response).getTotal()));

            final BooksByLocationResponse booksByLocationResponse = (BooksByLocationResponse)response;

            quikreadItemArrayList.clear();

            for(final QuikrItem quikrItem : booksByLocationResponse.getQuikritems()){

                restDataSource.searchBookByTitle(quikrItem.getTitle(), new Callback() {
                    @Override
                    public void success(Object o, Response response) {

                        BookSearchResponse bookSearchResponse = (BookSearchResponse)o;

                        try{
                            Log.i(TAG, bookSearchResponse.getBook().getAuthors().getAuthor().getName());
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }

                        String genre;

                        try{
                            genre = quikrItem.getAttributeGenre().getAsString();
                        }catch (Exception e){
                            genre= "";
                        }

                        QuikreadItem  quikreadItem = new QuikreadItem(
                                genre,
                                quikrItem.getPrice(),
                                bookSearchResponse.getBook().getTitle(),
                                bookSearchResponse.getBook().getImageUrl(),
                                bookSearchResponse.getBook().getDescription(),
                                bookSearchResponse.getBook().getAuthors().getAuthor().getName(),
                                bookSearchResponse.getBook().getAverageRating(),
                                bookSearchResponse.getBook().getIsbn()
                        );
                        quikreadItemArrayList.add(quikreadItem);
                        count++;

                        if(count == booksByLocationResponse.getQuikritems().size()){
                            requestCompleted();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getLocalizedMessage());
                        count++;

                        if(count == booksByLocationResponse.getQuikritems().size()){
                            requestCompleted();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onError(RetrofitError error) {

        Log.e(TAG, error.getMessage());
        Log.e(TAG, error.getLocalizedMessage());
    }

    public void requestCompleted(){
        Log.v(TAG, "Request Completed");

        for (QuikreadItem quikreadItem : quikreadItemArrayList){
            viewPagerAdapter.addFragment(BookItemFragment.newInstance(quikreadItem));
        }

        //TODO : notifyDataSetChanged here
        viewPager.setAdapter(viewPagerAdapter);
    }
}