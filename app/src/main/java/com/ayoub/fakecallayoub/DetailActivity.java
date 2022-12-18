package com.ayoub.fakecallayoub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ayoub.fakecallayoub.packs.AdsLoaded;

public class DetailActivity extends AppCompatActivity {
    RadioGroup rdgrp,rdgrp2,rdgrp3;
     public static boolean V1,V2,V3;
    public static String vCheked,socialcheked;
    int timecheked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AdsLoaded.loadbanner(this);
        AdsLoaded.LoadNative(this);
        rdgrp=findViewById(R.id.list_action);
        rdgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                V1=true;
                selectv(radioGroup,i);
            }
        });
        rdgrp2=findViewById(R.id.lict_form);
        rdgrp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                V2=true;
                socailchek(radioGroup, i);
            }
        });
        rdgrp3=findViewById(R.id.list_time);
        rdgrp3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                V3=true;
                timeselect(radioGroup, i);
            }
        });

    }
    private void selectv(RadioGroup group, int checkedRadioId) {
        checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.rdvoic) {
            vCheked="voice";
        } else if(checkedRadioId== R.id.rdvid ) {
            vCheked="video";
        }
    }
    private void socailchek(RadioGroup group, int checkedRadioId) {
         checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.rdfacebook) {
           socialcheked="facebook";
        } else if(checkedRadioId== R.id.rdwhatsap) {
            socialcheked="whatsapp";
        } else if(checkedRadioId== R.id.rdtelegram) {
            socialcheked="telegram";
        }
    }
    private void timeselect(RadioGroup group, int checkedRadioId) {
       checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.rdNow) {
            Handler h=new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timecheked=1;
                }
            },5000);

        } else if(checkedRadioId== R.id.rd10) {
            timecheked=10;
        }else if(checkedRadioId== R.id.rd30) {
            timecheked=30;
        }else if(checkedRadioId== R.id.rd60) {
            timecheked=60;
        }else if(checkedRadioId== R.id.rd300) {
            timecheked=300;
        }
    }
    public void setUpAlarm(){

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, FakeCallReceiver.class);
        PendingIntent fakePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timecheked * 1000, fakePendingIntent);

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),(5*1000),fakePendingIntent);
        Toast.makeText(getApplicationContext(), "Your fake call time has been set", Toast.LENGTH_SHORT).show();
        //this.finish();

    }

    public void setAl(View view) {
        AdsLoaded.loadaAdRew(DetailActivity.this);

        if(V1= V2 =V3=true){
            setUpAlarm();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),StartActivity.class));
        finish();
    }
}