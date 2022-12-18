package com.ayoub.fakecallayoub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayoub.fakecallayoub.packs.AdsLoaded;
import com.ayoub.fakecallayoub.packs.myapps;

public class ImageActivity extends AppCompatActivity {
    RecyclerView rc1;
    App_item_Adapter app_item_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
         AdsLoaded.LoadNative(this);
        rc1=findViewById(R.id.rc);
        app_item_adapter=new App_item_Adapter(this, myapps.appList);
        rc1.setAdapter(app_item_adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true);
        rc1.setLayoutManager(linearLayoutManager);
    }


    public void main(View view) {
        startActivity(new Intent(ImageActivity.this,DetailActivity.class));

        AdsLoaded.loadaAdInter(this);

    }
}