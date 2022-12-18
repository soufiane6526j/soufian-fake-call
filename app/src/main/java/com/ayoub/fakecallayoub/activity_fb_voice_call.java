package com.ayoub.fakecallayoub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class activity_fb_voice_call extends AppCompatActivity implements SurfaceHolder.Callback {
    private String networkCarrier;
    private MediaPlayer mp,MC;
    int i = 0;
    private long mStartTime = 0L;
    private TextView mTimeLabel;

    //handler to handle the message to the timer task
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_voice_call);
        final TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        networkCarrier = tm.getNetworkOperatorName();
        mTimeLabel=findViewById(R.id.txtcall);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.facebook);
        MC= MediaPlayer.create(getApplicationContext(),R.raw.audio1);

        mp.start();
        RelativeLayout btnn=findViewById(R.id.endCallbtn);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MC.stop();
                Intent start=new Intent(getApplicationContext() ,DetailActivity.class);
                startActivity(start);
                finish();
            }
        });



        RelativeLayout answerCall =  findViewById(R.id.answercall);
        RelativeLayout rejectCall =  findViewById(R.id.rejectcall);
        Handler h=new Handler();

        answerCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.stop();
                MC.start();
                if(mStartTime==0L){
                    mStartTime= SystemClock.uptimeMillis();
                    mHandler.removeCallbacks(mUpdateTimeTask);
                    mHandler.postDelayed(mUpdateTimeTask,100);
                }
                findViewById(R.id.laybawah2).setVisibility(View.VISIBLE);
                findViewById(R.id.laybawah1).setVisibility(View.GONE);

            }
        });

        rejectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.stop();
                Intent start=new Intent(getApplicationContext() ,DetailActivity.class);
                startActivity(start);
                finish();

            }
        });


    }
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            final long start = mStartTime;
            long millis = SystemClock.uptimeMillis() - start;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            mTimeLabel.setText("" +  String.format("%02d", minutes) + ":"
                    + String.format("%02d", seconds));
            mHandler.postDelayed(this, 200);
        }
    };
    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mUpdateTimeTask);
        super.onPause();
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}