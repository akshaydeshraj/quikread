package com.katana.quikread.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.katana.quikread.ActivityScope;
import com.katana.quikread.R;
import com.katana.quikread.common.BaseActivity;
import com.katana.quikread.components.AppComponent;
import com.katana.quikread.models.QuikrItem;
import com.katana.quikread.rest.OnRequestFinishedListener;
import com.katana.quikread.rest.RestDataSource;
import com.katana.quikread.rest.models.BookSearchResponse;
import com.katana.quikread.rest.models.BooksByLocationResponse;

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

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.main_viewpager)
    ViewPager viewPager;

    @Inject
    RestDataSource restDataSource;

    int plusCount = 0;
    int negativeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_content_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        restDataSource.setOnRequestFinishedListener(this);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),10));

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

            BooksByLocationResponse booksByLocationResponse = (BooksByLocationResponse)response;

            for(QuikrItem quikrItem : booksByLocationResponse.getQuikritems()){

                restDataSource.searchBookByTitle(quikrItem.getTitle(), new Callback() {
                    @Override
                    public void success(Object o, Response response) {
                        plusCount++;
                        Log.i(TAG, ((BookSearchResponse) o).getBook().getTitle());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        negativeCount++;
                        Log.e(TAG, error.getLocalizedMessage());
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

}