package com.katana.quikread.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickr_details);

        ButterKnife.bind(this);

        QuikreadItem quikreadItem = (QuikreadItem)getIntent().getSerializableExtra("quickread");

        city.setText(quikreadItem.getCityName());

        content.setText(""+quikreadItem.getQuickrContent());

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
