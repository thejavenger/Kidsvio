package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import eightbitlab.com.blurview.BlurView;

public class GuessTheMissingColor extends AppCompatActivity {


    private BlurView colorbutton1;
    private BlurView colorbutton2;
    private BlurView colorbutton3;

    private TextView colorname1;
    private TextView colorname2;
    private TextView colorname3;

    private BlurView[] colorbuttons;
    private TextView[] colornames;

    private ImageView imageView;

    private TextView textgame;
    private TextView textscore;
    private TextView textalltimescore;

    int gameid;

    int gamecount = 0;
    int totalgame = 10;
    int gamescore = 0;
    int gamealltimescore = 0;
    int gameallpoints = 0;

    int[] imageguess = new int[]{
            R.drawable.missing_blue_spiderman2,
            R.drawable.missing_brown_potato, R.drawable.missing_brown_spongebob,
            R.drawable.missing_brown_tree, R.drawable.missing_brown_tree1,
            R.drawable.missing_gold_loki, R.drawable.missing_gold_thanos5,
            R.drawable.missing_green_hulk, R.drawable.missing_green_hulk1, R.drawable.missing_green_loki,
            R.drawable.missing_green_tree, R.drawable.missing_green_tree1,
            R.drawable.missing_orange_orange1,
            R.drawable.missing_pink_patrickstar, R.drawable.missing_pink_pig, R.drawable.missing_pink_strawberry,
            R.drawable.missing_red_apple, R.drawable.missing_red_apple1, R.drawable.missing_red_ironman,
            R.drawable.missing_red_ironman1, R.drawable.missing_red_mrkrab1, R.drawable.missing_red_spiderman2,
            R.drawable.missing_violet_eggplant, R.drawable.missing_violet_eggplant1, R.drawable.missing_violet_hulk,
            R.drawable.missing_violet_hulk1, R.drawable.missing_violet_patrickstar, R.drawable.missing_violet_thanos5,
            R.drawable.missing_yellow_banana, R.drawable.missing_yellow_banana1, R.drawable.missing_yellow_boruto,
            R.drawable.missing_yellow_ironman, R.drawable.missing_yellow_ironman1, R.drawable.missing_yellow_spongebob,
            R.drawable.missing_yellow_sun, R.drawable.missing_yellow_sun1
    };

    int[] imagefull = new int[]{
            R.drawable.fullcolor_spiderman2,
            R.drawable.fullcolor_potato, R.drawable.fullcolor_spongebob,
            R.drawable.fullcolor_tree, R.drawable.fullcolor_tree1,
            R.drawable.fullcolor_loki, R.drawable.fullcolor_thanos5,
            R.drawable.fullcolor_hulk, R.drawable.fullcolor_hulk1, R.drawable.fullcolor_loki,
            R.drawable.fullcolor_tree, R.drawable.fullcolor_tree1,
            R.drawable.fullcolor_orange1,
            R.drawable.fullcolor_patrickstar, R.drawable.fullcolor_pig, R.drawable.fullcolor_strawberry,
            R.drawable.fullcolor_apple, R.drawable.fullcolor_apple1, R.drawable.fullcolor_ironman,
            R.drawable.fullcolor_ironman1, R.drawable.fullcolor_mrkrab1, R.drawable.fullcolor_spiderman2,
            R.drawable.fullcolor_eggplant, R.drawable.fullcolor_eggplant1, R.drawable.fullcolor_hulk,
            R.drawable.fullcolor_hulk1, R.drawable.fullcolor_patrickstar, R.drawable.fullcolor_thanos5,
            R.drawable.fullcolor_banana01, R.drawable.fullcolor_banana1, R.drawable.fullcolor_boruto,
            R.drawable.fullcolor_ironman, R.drawable.fullcolor_ironman1, R.drawable.fullcolor_spongebob,
            R.drawable.fullcolor_sun, R.drawable.fullcolor_sun1
    };

    String[] rightcolor = new String[]{
            "blue",
            "brown", "brown",
            "brown", "brown",
            "gold", "gold",
            "green", "green", "green",
            "green", "green",
            "orange",
            "pink", "pink", "pink",
            "red", "red", "red",
            "red", "red", "red",
            "violet", "violet", "violet",
            "violet", "violet", "violet",
            "yellow", "yellow","yellow",
            "yellow", "yellow","yellow",
            "yellow", "yellow"
    };

    String[] allcolortexts = new String[]{
            "blue", "brown", "gold", "green", "orange", "pink", "red", "violet", "yellow"
    };

    int[] allcolors = new int[]{
            Color.BLUE,
            Color.rgb(185, 122, 87),
            Color.rgb(128, 128, 0),
            Color.GREEN,
            Color.rgb(255, 128, 0),
            Color.rgb(255, 174, 201),
            Color.RED,
            Color.rgb(163, 73, 164),
            Color.YELLOW
    };

    int[] randoms;
    String[] randomnames;

    int newimageguess;
    int answerpos;

    DBHandler handler;
    Score guessthemissingcolorscore;


    String GAMENAME = "GuessTheMissingColor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guess_the_missing_color);

        handler = new DBHandler(this, null, null, 1);

        textgame = (TextView) findViewById(R.id.textGame);
        textscore = (TextView) findViewById(R.id.textScore);
        textalltimescore = (TextView) findViewById(R.id.textAllTimeScore);

        getGameScores();

        shuffleImages();
    }

    public void getGameScores(){

        if(handler.isGameDataEmpty(GAMENAME)){

            handler.initializeGame(GAMENAME);
        }

        guessthemissingcolorscore = handler.getGameScore(GAMENAME);


        if(null!=guessthemissingcolorscore){

            gameid = guessthemissingcolorscore.get_id();

            gamealltimescore = guessthemissingcolorscore.get_score();
            gameallpoints = guessthemissingcolorscore.get_allpoints();
        }



    }


    private void shuffleImages(){

        if(gamecount<totalgame){
            gamecount++;
            gameallpoints++;

            if(null!=guessthemissingcolorscore){
                guessthemissingcolorscore.set_allpoints(gameallpoints);
                handler.updateScore(guessthemissingcolorscore);
            }


            updateScoreBoard();

            colorbutton1 = (BlurView) findViewById(R.id.blurview1);
            colorbutton2 = (BlurView) findViewById(R.id.blurview2);
            colorbutton3 = (BlurView) findViewById(R.id.blurview3);

            colorbuttons = new BlurView[]{colorbutton1, colorbutton2, colorbutton3};

            colorname1 = (TextView) findViewById(R.id.colorName1);
            colorname2 = (TextView) findViewById(R.id.colorName2);
            colorname3 = (TextView) findViewById(R.id.colorName3);

            colornames = new TextView[]{colorname1, colorname2, colorname3};

            imageView = (ImageView) findViewById(R.id.imageView);

            int answercolor = Color.BLACK;

            Random rand = new Random();
            newimageguess = rand.nextInt(imageguess.length);

            imageView.setImageResource(imageguess[newimageguess]);


            for(int i=0;i<allcolortexts.length;i++){
                if(allcolortexts[i].equals(rightcolor[newimageguess])){
                    answercolor = allcolors[i];
                }
            }

            answerpos = rand.nextInt(colorbuttons.length);

            if(null==randoms){

                randoms = new int[colorbuttons.length];
                randomnames = new String[colorbuttons.length];
            }




            for(int i=0;i<colorbuttons.length;i++){

                int newrand = rand.nextInt(allcolortexts.length);
                int randcolor;


                if(i==answerpos){
                    //colorbuttons[i].setBackgroundColor(answercolor);
                    randoms[i] = answercolor;
                    randomnames[i] = rightcolor[newimageguess];
                }else{

                    if(i==0){

                        boolean sameasanswercolor;

                        do{
                            sameasanswercolor = false;

                            if(answercolor==allcolors[newrand]){
                                sameasanswercolor = true;
                                newrand = rand.nextInt(allcolortexts.length);
                            }

                        }while(sameasanswercolor);

                        randoms[i] = allcolors[newrand];
                        randomnames[i] = allcolortexts[newrand];
                    }else{

                        boolean usedrand;

                        do{

                            boolean sameasanswercolor;

                            do{
                                sameasanswercolor = false;

                                if(answercolor==allcolors[newrand]){
                                    sameasanswercolor = true;
                                    newrand = rand.nextInt(allcolortexts.length);
                                }

                            }while(sameasanswercolor);

                            usedrand = false;
                            for(int ii=0;ii<i;ii++){
                                if(allcolors[newrand]==randoms[ii]){
                                    usedrand = true;
                                    newrand = rand.nextInt(allcolortexts.length);
                                }
                            }

                        }while(usedrand);

                        randoms[i] = allcolors[newrand];
                        randomnames[i] = allcolortexts[newrand];
                    }
                }
            }

            for(int i=0;i<colorbuttons.length;i++){
                colorbuttons[i].setBackgroundColor(randoms[i]);
                colornames[i].setText(randomnames[i].toUpperCase());
            }
        }else{

            if(gamescore==gamecount){
                Intent i = new Intent(this, ClaimReward.class);
                startActivity(i);
            }else{
                Intent i = new Intent(this, GameAfter.class);

                i.putExtra("gamescore", gamescore);
                i.putExtra("gamealltimescore", gamealltimescore);
                i.putExtra("gameallpoints", gameallpoints);
                i.putExtra("GAMENAME", "Guess The Missing Color");
                startActivity(i);
            }


        }



    }


    public void clickedAnswer(View view){

        BlurView clickedblurview = (BlurView) view;

        colorbutton1 = (BlurView) findViewById(R.id.blurview1);
        colorbutton2 = (BlurView) findViewById(R.id.blurview2);
        colorbutton3 = (BlurView) findViewById(R.id.blurview3);

        colorbuttons = new BlurView[]{colorbutton1, colorbutton2, colorbutton3};



        if(clickedblurview.equals(colorbuttons[answerpos])){
            imageView.setImageResource(imagefull[newimageguess]);

            gamescore++;
            gamealltimescore++;

            if(null!=guessthemissingcolorscore){
                guessthemissingcolorscore.set_score(gamealltimescore);
                handler.updateScore(guessthemissingcolorscore);
            }

            Toast.makeText(GuessTheMissingColor.this, "CORRECT! Clicked on " + rightcolor[newimageguess].toUpperCase(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(GuessTheMissingColor.this, "WRONG! The correct answer is " + rightcolor[newimageguess].toUpperCase(), Toast.LENGTH_SHORT).show();
        }

        updateScoreBoard();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shuffleImages();
            }
        }, 3000);

    }

    public void updateScoreBoard(){
        textgame.setText(gamecount + "/" + totalgame);
        textscore.setText(gamescore + "");
        textalltimescore.setText(gamealltimescore + "");
    }

    public void reshuffle(View view){
        shuffleImages();
    }


    public void onBackPressed() {
        Intent i = new Intent(this, FunActivitiesAndGames.class);
        startActivity(i);
    }

}