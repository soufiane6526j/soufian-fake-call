package com.ayoub.fakecallayoub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.ayoub.fakecallayoub.packs.AdsLoaded;

import java.util.List;

public class activity_fb_video_call extends AppCompatActivity implements SurfaceHolder.Callback{
    Camera camera;
    SurfaceView surfaceView,surfaceView1;
    int Counter;
    ImageView end_Call;
    private MediaPlayer mp;
    LinearLayout acceptt;
    VideoView palyer_video;
    ImageView CheckimageVideo;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_video_call);
        palyer_video = findViewById(R.id.videoView4);
        RelativeLayout btnn=findViewById(R.id.laytolak2);
        surfaceView = findViewById(R.id.surfaceView4);
        mp= MediaPlayer.create(getApplicationContext(),R.raw.facebook);
        mp.start();

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsLoaded.loadaAdInter(activity_fb_video_call.this );
                Intent start=new Intent(getApplicationContext() ,DetailActivity.class);
                startActivity(start);
                finish();
            }
        });
    }



    public void CameraShow(){
        surfaceView.getHolder().addCallback(this);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        cam_video();

    }

    public void acceptVideoCall(View view) {
        mp.stop();


                palyer_video.setVisibility(View.VISIBLE);
        StartAppelVideo();

        CameraShow();
                surfaceView.setVisibility(View.VISIBLE);

                findViewById(R.id.icnns).setVisibility(View.GONE);
                findViewById(R.id.imgback).setVisibility(View.GONE);
                findViewById(R.id.imgback1).setVisibility(View.GONE);

                findViewById(R.id.laybawah2).setVisibility(View.VISIBLE);
                findViewById(R.id.laybawah1).setVisibility(View.GONE);



     }
    private void StartAppelVideo() {
        palyer_video.setVideoURI(Uri.parse("android.resource://" +getPackageName() + "/" + R.raw.video1));
        palyer_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.setLooping(true);

            }
        });

        palyer_video.start();
    }
    public void cam_video(){
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

        Counter = Camera.getNumberOfCameras();
        for (int FacingCam = 0; FacingCam < Counter; FacingCam++) {
            Camera.getCameraInfo(FacingCam, cameraInfo);
            if (cameraInfo.facing == 1) {
                try {
                    camera = Camera.open(FacingCam);
                } catch (RuntimeException e) {
                    //do nothing
                }
            }
        }

    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        try {
            camera.setPreviewDisplay(surfaceView.getHolder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        try{
            Camera.Parameters params = camera.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();
            Camera.Size selected = sizes.get(0);
            params.setPreviewSize(selected.width,selected.height);
            camera.setParameters(params);
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }catch (Exception e0){
            //error to Initialize Camera
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    @Override
    public void onPause() {
        super.onPause();
        //camera.stopPreview();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //camera.release();
    }

    public void endcall(View view) {
        mp.stop();
        Intent start=new Intent(getApplicationContext() ,DetailActivity.class);
        startActivity(start);
        finish();

    }
}