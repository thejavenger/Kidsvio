package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ListenAudioClips extends AppCompatActivity {


    boolean ignoreclick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_listen_audio_clips);
    }

    public void goToMoralStories(View view){
        if(!ignoreclick) {
            ignoreclick = true;
            //Intent i = new Intent(this, MoralStories.class);

            Bundle bundle = new Bundle();
            bundle.putString("audio", "Moral Stories");

            Intent i = new Intent(this, AudioList.class);
            i.putExtras(bundle);

            startActivity(i);
            finish();
        }
    }

    public void goToLullabies(View view){
        if(!ignoreclick) {
            ignoreclick = true;
            //Intent i = new Intent(this, Lullabies.class);

            Bundle bundle = new Bundle();
            bundle.putString("audio", "Lullabies");

            Intent i = new Intent(this, AudioList.class);
            i.putExtras(bundle);
            startActivity(i);
            finish();
        }
    }

    public void goBackToCategories(View view){
        if(!ignoreclick) {
            ignoreclick = true;
            Intent i = new Intent(this, Categories.class);
            startActivity(i);
            finish();
        }
    }

    public void onBackPressed() {

        Intent i = new Intent(this, Categories.class);
        startActivity(i);
    }

}