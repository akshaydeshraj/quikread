package com.katana.quikread.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.polok.routedrawer.RouteDrawer;
import com.github.polok.routedrawer.RouteRest;
import com.github.polok.routedrawer.model.Routes;
import com.github.polok.routedrawer.model.TravelMode;
import com.github.polok.routedrawer.parser.RouteJsonParser;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.katana.quikread.R;
import com.katana.quikread.models.QuikreadItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author DEEPANKAR
 * @since 12-09-2015.
 */
public class BookDetailsActivity extends AppCompatActivity {

    @Bind(R.id.details_city_name)
    TextView city;

    @Bind(R.id.details_locality)
    TextView locality;

    @Bind(R.id.details_content)
    TextView content;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickr_details);

        ButterKnife.bind(this);

        QuikreadItem quikreadItem = (QuikreadItem)getIntent().getSerializableExtra("quickread");

        city.setText(quikreadItem.getCityName());

        content.setText("" + quikreadItem.getQuickrContent());

        googleMap = ((com.google.android.gms.maps.MapFragment)getFragmentManager().findFragmentById(R.id.details_map)).getMap();

        final RouteDrawer routeDrawer = new RouteDrawer.RouteDrawerBuilder(googleMap)
                .withColor(Color.BLUE)
                .withWidth(8)
                .withAlpha(0.5f)
                .withMarkerIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .build();

        RouteRest routeRest = new RouteRest();

        routeRest.getJsonDirections(new LatLng(50.126922, 19.015261), new LatLng(50.200206, 19.175603), TravelMode.DRIVING)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Routes>() {
                    @Override
                    public Routes call(String s) {
                        return new RouteJsonParser<Routes>().parse(s, Routes.class);
                    }
                })
                .subscribe(new Action1<Routes>() {
                    @Override
                    public void call(Routes r) {
                        routeDrawer.drawPath(r);
                    }
                });

    }

    private void call(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        try {
            startActivity(intent);

        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

}
