package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class NameTheAnimal extends AppCompatActivity {

    private ImageButton animalbutton1;
    private ImageButton animalbutton2;
    private ImageButton animalbutton3;

    private TextView textgame;
    private TextView textscore;
    private TextView textalltimescore;

    int gameid;

    int gamecount = 0;
    int totalgame = 10;
    int gamescore = 0;
    int gamealltimescore = 0;
    int gameallpoints = 0;

    String[] animal = new String[]{"dog", "cat", "bird", "bat", "crocodile",
            "elephant", "frog", "goat", "horse", "lion", "monkey", "owl", "pig", "rooster"};

    int[] drawables = new int[]{R.drawable.dog_colored, R.drawable.cat_colored, R.drawable.bird_colored, R.drawable.bat_colored,
            R.drawable.crocodile_colored, R.drawable.elephant_colored, R.drawable.frog_colored, R.drawable.goat_colored, R.drawable.horse_colored,
            R.drawable.lion_colored, R.drawable.monkey_colored, R.drawable.owl_colored, R.drawable.pig_colored, R.drawable.rooster_colored};

    int[] sounds = new int[]{R.raw.dog, R.raw.cat, R.raw.bird, R.raw.bat,
            R.raw.crocodile, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.horse,
            R.raw.lion, R.raw.monkey, R.raw.owl, R.raw.pig, R.raw.rooster};

    private ImageButton[] animalbutton;
    private TextView guessanswer;

    MediaPlayer soundeffect;

    int answer;
    int[] randoms;

    DBHandler handler;
    Score nametheanimalscore;

    List<Score> allscores;


    String GAMENAME = "NameTheAnimal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_name_the_animal);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        handler = new DBHandler(this, null, null, 1);

        textgame = (TextView) findViewById(R.id.textGame);
        textscore = (TextView) findViewById(R.id.textScore);
        textalltimescore = (TextView) findViewById(R.id.textAllTimeScore);

        getGameScores();


        shuffleRandomAnimal();
    }

    @Override
    public void onBackPressed() {
        if (null != soundeffect && soundeffect.isPlaying())
            soundeffect.stop();

        Intent i = new Intent(this, FunActivitiesAndGames.class);
        startActivity(i);
    }

    public void getGameScores() {

        if (handler.isGameDataEmpty(GAMENAME)) {

            //nametheanimalscore = new Score();

            //nametheanimalscore.set_gamename(GAMENAME);
            //nametheanimalscore.set_score(0);
            //nametheanimalscore.set_allpoints(0);
            //nametheanimalscore.set_status("--");

            //handler.addScore(nametheanimalscore);

            handler.initializeGame(GAMENAME);
        }

        //for (Score score : allscores = handler.getAllScores()) {
        //if(score.get_gamename().equalsIgnoreCase(GAMENAME)){
        //nametheanimalscore = score;
        //}
        //}

        nametheanimalscore = handler.getGameScore(GAMENAME);


        if (null != nametheanimalscore) {

            gameid = nametheanimalscore.get_id();

            gamealltimescore = nametheanimalscore.get_score();
            gameallpoints = nametheanimalscore.get_allpoints();
        }


    }

    public void shuffleRandomAnimal() {

        if (gamecount < totalgame) {
            gamecount++;
            gameallpoints++;

            if (null != nametheanimalscore) {
                nametheanimalscore.set_allpoints(gameallpoints);
                handler.updateScore(nametheanimalscore);
            }


            updateScoreBoard();

            animalbutton1 = (ImageButton) findViewById(R.id.btnAnimal1);
            animalbutton2 = (ImageButton) findViewById(R.id.btnAnimal2);
            animalbutton3 = (ImageButton) findViewById(R.id.btnAnimal3);

            animalbutton = new ImageButton[]{animalbutton1, animalbutton2, animalbutton3};
            randoms = new int[3];

            guessanswer = (TextView) findViewById(R.id.txtAnswer);

            if (null != soundeffect && soundeffect.isPlaying())
                soundeffect.stop();

            //uessanswer.setText("");

            //Toast.makeText(this, "Before For Loop", Toast.LENGTH_LONG).show();

            for (int i = 0; i < randoms.length; i++) {

                Random rand = new Random();
                int newrand = rand.nextInt(animal.length);

                if (i == 0) {
                    randoms[i] = newrand;
                } else {

                    boolean usedrand;

                    do {
                        usedrand = false;
                        for (int ii = 0; ii < i; ii++) {
                            if (newrand == randoms[ii]) {
                                usedrand = true;
                                newrand = rand.nextInt(animal.length);
                            }
                        }

                    } while (usedrand);

                    randoms[i] = newrand;
                }
            }

            //Toast.makeText(this, "After For Loop", Toast.LENGTH_LONG).show();

            Random rand = new Random();
            answer = rand.nextInt(3);

            soundeffect = MediaPlayer.create(this, sounds[randoms[answer]]);
            soundeffect.start();

            if (null != soundeffect && soundeffect.isPlaying()) {
                for (int i = 0; i < randoms.length; i++) {
                    int r = randoms[i];

                    animalbutton[i].setImageResource(drawables[r]);
                }


            }
        } else {

            if (gamescore == gamecount) {
                if (null != soundeffect && soundeffect.isPlaying())
                    soundeffect.stop();

                Intent i = new Intent(this, ClaimReward.class);
                startActivity(i);
            } else {
                if (null != soundeffect && soundeffect.isPlaying())
                    soundeffect.stop();

                Intent i = new Intent(this, GameAfter.class);

                i.putExtra("gamescore", gamescore);
                i.putExtra("gamealltimescore", gamealltimescore);
                i.putExtra("gameallpoints", gameallpoints);
                i.putExtra("GAMENAME", "Name The Animal");
                startActivity(i);

            }
        }


    }


    public void createRandomAnimalGuess(View view) {
        shuffleRandomAnimal();
    }

    public void clickAnswer(View view) {

        int guess = 3;

        animalbutton1 = (ImageButton) findViewById(R.id.btnAnimal1);
        animalbutton2 = (ImageButton) findViewById(R.id.btnAnimal2);
        animalbutton3 = (ImageButton) findViewById(R.id.btnAnimal3);

        animalbutton = new ImageButton[]{animalbutton1, animalbutton2, animalbutton3};

        guessanswer = (TextView) findViewById(R.id.txtAnswer);

        for (int i = 0; i < animalbutton.length; i++) {
            if (animalbutton[i].equals((ImageButton) view)) {
                guess = i;
            }
        }

        guessanswer.setTextColor(Color.WHITE);

        if (guess == answer) {
            //Toast.makeText(this, "CORRECT!! It's " + animal[randoms[answer]], Toast.LENGTH_LONG).show();
            gamescore++;
            gamealltimescore++;

            if (null != nametheanimalscore) {
                nametheanimalscore.set_score(gamealltimescore);
                handler.updateScore(nametheanimalscore);
            }

            guessanswer.setText("CORRECT!! It's " + animal[randoms[answer]]);
        } else {
            guessanswer.setTextColor(Color.RED);
            guessanswer.setText("WRONG! The answer is " + animal[randoms[answer]]);
            //Toast.makeText(this, "WRONG! The answer is " + animal[randoms[answer]], Toast.LENGTH_LONG).show();
        }

        updateScoreBoard();

        shuffleRandomAnimal();
    }

    public void updateScoreBoard() {
        textgame.setText(gamecount + "/" + totalgame);
        textscore.setText(gamescore + "");
        textalltimescore.setText(gamealltimescore + "");
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (null != soundeffect && soundeffect.isPlaying())
            soundeffect.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (null != soundeffect && soundeffect.isPlaying())
            soundeffect.stop();
    }

    /*public void sampleClick(View view) {
        ImageButton aButton = (ImageButton) view;
        aButton.setImageResource(R.drawable.cat);
    }*/

}