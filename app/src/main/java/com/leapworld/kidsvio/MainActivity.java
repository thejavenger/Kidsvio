package com.leapworld.kidsvio;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;

    int backgroundmusic = R.raw.audbg_play_date;
    int tap = R.raw.aud_plasticbubble;

    DBHandler handler;

    boolean musicon;
    boolean soundon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        handler = new DBHandler(this, null, null, 1);

        if (handler.isSettingsEmpty()) {
            handler.initializeSettings();

            musicon = true;
            soundon = true;

        } else {
            Setting musicsetting = handler.getMusicSetting();
            Setting soundsetting = handler.getSoundSetting();

            musicon = musicsetting.get_intvalue() > 0;
            soundon = soundsetting.get_intvalue() > 0;
        }

        if (musicon)
            playBackgroundMusic();


        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void playBackgroundMusic() {
        if(player!=null){
            player.release();;
            player = null;
        }

        player = MediaPlayer.create(this, backgroundmusic);
        player.start();
    }

    public void playTap() {

        /*tap.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                tap.reset();
                tap.release();
                tap = null;

            }
        });*/

        if(player!=null){
            player.release();;
            player = null;
        }

        player = MediaPlayer.create(this, tap);
        player.start();
    }

    public void getStarted(View view) {
        if (null != player && player.isPlaying())
            player.stop();

        if (soundon)
            playTap();


        //Intent i = new Intent(this, ApproachOption.class);
        Intent i = new Intent(MainActivity.this, CustomLoadingScreen.class);
        startActivity(i);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exitApp(View view) {

        if (soundon)
            playTap();

        finishAffinity();
        finish();
        System.exit(0);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
            finishAffinity();
        finish();
        System.exit(0);
    }
}