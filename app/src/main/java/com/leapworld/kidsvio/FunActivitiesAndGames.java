package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FunActivitiesAndGames extends AppCompatActivity {


    boolean ignoreclick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_fun_activities_and_games);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void backHome(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void goToGuessTheAnimal(View view){
        if(!ignoreclick) {
            ignoreclick = true;
            Intent i = new Intent(this, NameTheAnimal.class);
            startActivity(i);
            finish();
        }
    }

    public void goToGuessMissingColor(View view){
        if(!ignoreclick) {
            ignoreclick = true;
            Intent i = new Intent(this, GuessTheMissingColor.class);
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