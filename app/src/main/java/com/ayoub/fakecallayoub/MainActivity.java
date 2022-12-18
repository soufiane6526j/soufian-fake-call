package com.ayoub.fakecallayoub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayoub.fakecallayoub.packs.AdsLoaded;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AdsLoaded.LoadNative(this);
        AdsLoaded.loadbanner(this);
    }

    public void main(View view) {
        startActivity(new Intent(getApplicationContext(),StartActivity.class));
        AdsLoaded.loadaAdInter(this );
    }
}