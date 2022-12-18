package com.ayoub.fakecallayoub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.ayoub.fakecallayoub.packs.AdsLoaded;

import java.util.List;

public class activity_tele_video_call extends AppCompatActivity implements SurfaceHolder.Callback{
    Camera camera;
    SurfaceView surfaceView,surfaceView1;
    int Counter;
    ImageView end_Call;
    private MediaPlayer mp;
    VideoView palyer_video;
    ImageView CheckimageVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_video_call);
        palyer_video = findViewById(R.id.videoView);
        surfaceView1=findViewById(R.id.surfaceView2);
        surfaceView1.setVisibility(View.GONE);
        CameraShow();
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
        palyer_video.setVisibility(View.GONE);
        ImageView btnn=findViewById(R.id.endcalls);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsLoaded.loadaAdInter(activity_tele_video_call.this);
                Intent start=new Intent(getApplicationContext() ,DetailActivity.class);
                startActivity(start);
                finish();
            }
        });

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

    public void CameraShow(){
        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        cam_video();

    }
    public void CameraShow1(){
        surfaceView1.getHolder().addCallback(this);
        surfaceView1.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        cam_video();

    }
    public void acceptVideoCall(View view) {
        mp.stop();
        palyer_video.setVisibility(View.VISIBLE);
        StartAppelVideo();
        CameraShow1();
        surfaceView1.setVisibility(View.VISIBLE);
        findViewById(R.id.surfaceView).setVisibility(View.GONE);
        findViewById(R.id.bawah).setVisibility(View.VISIBLE);
        findViewById(R.id.calls).setVisibility(View.GONE);
        findViewById(R.id.lnr).setVisibility(View.GONE);


        //findViewById(R.id.atas).setVisibility(View.GONE);
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
        camera.stopPreview();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        camera.release();
    }

    public void endcall(View view) {
        mp.stop();
        Intent start=new Intent(getApplicationContext() ,DetailActivity.class);
        startActivity(start);
        finish();

    }
}