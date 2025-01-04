package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    DBHandler handler;

    Switch musicswitch;
    Switch soundswitch;

    Setting musicsetting;
    Setting soundsetting;


    int musiconint = 0;
    int soundonint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_settings);

        handler = new DBHandler(this, null, null, 1);

        musicswitch = (Switch) findViewById(R.id.musicswitch);
        soundswitch = (Switch) findViewById(R.id.soundswitch);

        getSettings();
    }

    public void getSettings(){
        musicsetting = handler.getMusicSetting();
        soundsetting = handler.getSoundSetting();

        musicswitch.setChecked(musicsetting.get_intvalue()>0);
        soundswitch.setChecked(soundsetting.get_intvalue()>0);
    }

    public void saveSettings(View view){

        if(musicswitch.isChecked())
            musiconint = 1;
        else
            musiconint = 0;

        if(soundswitch.isChecked())
            soundonint = 1;
        else
            soundonint = 0;

        musicsetting.set_intvalue(musiconint);
        soundsetting.set_intvalue(soundonint);

        handler.updateSetting(musicsetting);
        handler.updateSetting(soundsetting);
    }

    public void goToRewards(View view){
        Intent i = new Intent(this, Rewards.class);
        startActivity(i);
    }


    public void onBackPressed() {

        Intent i = new Intent(this, Categories.class);
        startActivity(i);
    }
}