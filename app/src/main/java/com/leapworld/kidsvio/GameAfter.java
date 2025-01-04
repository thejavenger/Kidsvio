package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GameAfter extends AppCompatActivity {


    DBHandler handler;

    private TextView textgamename;
    private TextView textscore;
    private TextView textalltimescore;

    int gameid;

    int gamescore;
    int gamealltimescore;
    int gameallpoints;

    Score scoredata;


    String GAMENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_after);

        Bundle bundle = getIntent().getExtras();

        gamescore = bundle.getInt("gamescore");
        gamealltimescore = bundle.getInt("gamealltimescore");
        gameallpoints = bundle.getInt("gameallpoints");
        GAMENAME = bundle.getString("GAMENAME");

        textgamename = (TextView) findViewById(R.id.textGameName);
        textscore = (TextView) findViewById(R.id.textScore);
        textalltimescore = (TextView) findViewById(R.id.textAllTimeScore);

        textgamename.setText(GAMENAME);

        textscore.setText(gamescore + "");
        textalltimescore.setText(gamealltimescore + "");

    }

    public void backHome(View view){

        Intent i = new Intent(this, FunActivitiesAndGames.class);
        startActivity(i);
    }

    public void onBackPressed() {

        Intent i = new Intent(this, FunActivitiesAndGames.class);
        startActivity(i);
    }

}