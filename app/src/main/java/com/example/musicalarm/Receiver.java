package com.example.musicalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class Receiver extends BroadcastReceiver {

    MediaPlayer mediaPlayer;
    private String position;
    Ringtone ringtoneManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

       position=intent.getStringExtra("pos");
       Uri uri=Uri.parse(position);
        ringtoneManager=RingtoneManager.getRingtone(context,uri);
        ringtoneManager.play();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ringtoneManager.stop();
            }
        },90*1000);
    }
}
