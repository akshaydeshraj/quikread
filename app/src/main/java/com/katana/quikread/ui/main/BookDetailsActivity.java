package com.katana.quikread.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.katana.quikread.models.QuikreadItem;

/**
 * @author DEEPANKAR
 * @since 12-09-2015.
 */
public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuikreadItem quikreadItem = (QuikreadItem)getIntent().getSerializableExtra("quickread");
        
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
