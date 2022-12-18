package com.ayoub.fakecallayoub;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FakeCallReceiver extends BroadcastReceiver {
    NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(DetailActivity.vCheked.equalsIgnoreCase("voice")){
            if(DetailActivity.socialcheked.equalsIgnoreCase("facebook")){
                Intent intentObject = new Intent(context.getApplicationContext(), activity_fb_voice_call.class);
                intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intentObject);
            }else  if(DetailActivity.socialcheked.equalsIgnoreCase("whatsapp")){
                Intent intentObject = new Intent(context.getApplicationContext(), activity_voice_call.class);
                intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intentObject);
            }else if(DetailActivity.socialcheked.equalsIgnoreCase("telegram")){
                Intent intentObject = new Intent(context.getApplicationContext(), activity_telegrame_voice_call.class);
                intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intentObject);
            }
        }else if(DetailActivity.vCheked.equalsIgnoreCase("video")) {
            if(DetailActivity.socialcheked.equalsIgnoreCase("facebook")){
                Intent intentObject = new Intent(context.getApplicationContext(), activity_fb_video_call.class);
                intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intentObject);
            }else  if(DetailActivity.socialcheked.equalsIgnoreCase("whatsapp")){
                Intent intentObject = new Intent(context.getApplicationContext(), activity_video_call.class);
                intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intentObject);
            }else if(DetailActivity.socialcheked.equalsIgnoreCase("telegram")){
                Intent intentObject = new Intent(context.getApplicationContext(), activity_tele_video_call.class);
                intentObject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intentObject);
            }
        }






    }
}
