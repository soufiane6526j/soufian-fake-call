package com.ayoub.fakecallayoub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayoub.fakecallayoub.packs.AdsLoaded;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        AdsLoaded.loadbanner(this);
        AdsLoaded.LoadNative(this);
    }

    public void callstart(View view) {
        startActivity(new Intent(getApplicationContext(),ImageActivity.class));

        AdsLoaded.loadaAdInter(this );

    }



    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }*/
}