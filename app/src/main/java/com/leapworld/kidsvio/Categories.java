package com.leapworld.kidsvio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Categories extends AppCompatActivity {

    MediaPlayer backgroundmusic;
    MediaPlayer tap;
    DBHandler handler;

    boolean musicon;
    boolean soundon;

    boolean ignoreclick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_categories);

        handler = new DBHandler(this, null, null, 1);

        Setting musicsetting = handler.getMusicSetting();
        Setting soundsetting = handler.getSoundSetting();

        musicon = musicsetting.get_intvalue()>0;
        soundon = soundsetting.get_intvalue()>0;

        if(musicon)
            playBackgroundMusic();
    }

    @SuppressLint("RestrictedApi")
    public void showSettings(View view){
        /*MenuBuilder menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_example, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, view);
        optionsMenu.setForceShowIcon(true);

        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.heart:
                        goToFeedback();
                        return true;
                    case R.id.thoughts:
                        goToFAQ();
                        return true;
                    case R.id.settings:
                        goToSettings();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onMenuModeChange(@NonNull MenuBuilder menu) {

            }
        });
        optionsMenu.show();*/

        if(soundon)
            playTap();

        CategoriesPopUp categoriesPopUp = new CategoriesPopUp(this, musicon, soundon);
        categoriesPopUp.showPopupWindow(view);

    }

    //@Override
    //public void onBackPressed() {
        //Intent i = new Intent(this, MainActivity.class);
        //startActivity(i);

       //super.onBackPressed();
    //}

    public void playBackgroundMusic(){
        if(backgroundmusic!=null){
            backgroundmusic.release();;
            backgroundmusic = null;
        }

        backgroundmusic = MediaPlayer.create(this, R.raw.audbg_curious_kiddo);
        backgroundmusic.start();
    }

    public void playTap(){
        if(tap!=null){
            tap.release();;
            tap = null;
        }

        tap = MediaPlayer.create(this, R.raw.aud_plasticbubble);
        tap.start();
    }

    /*public boolean onCreateOptionsMenu(Menu menu){

        menu.add(0,1,1, memuIconWithText(getResources().getDrawable(R.drawable.heart_circle), "Optiom 1"));
        menu.add(0,2,2, memuIconWithText(getResources().getDrawable(R.drawable.thoughts), "Optiom 2"));
        menu.add(0,3,3, memuIconWithText(getResources().getDrawable(R.drawable.gear), "Optiom 3"));

        return true;
    }*/

    private CharSequence memuIconWithText(Drawable r, String title){
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("      " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }


    public void backHome(View view){
        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void goToNurseryRhymes(View view){
        if(!ignoreclick){
            ignoreclick = true;
            if(null!=backgroundmusic&&backgroundmusic.isPlaying())
                backgroundmusic.stop();

            if(soundon)
                playTap();

            //Intent i = new Intent(this, NurseryRhymes.class);
            Intent i = new Intent(this, NurseryRhymesGridView.class);
            startActivity(i);
            finish();
        }
    }

    public void goToAudioClips(View view){
        if(!ignoreclick){
            ignoreclick = true;
            if(null!=backgroundmusic&&backgroundmusic.isPlaying())
                backgroundmusic.stop();

            if(soundon)
                playTap();

            Intent i = new Intent(this, ListenAudioClips.class);
            startActivity(i);
            finish();
        }

    }

    public void goToFunActivitiesAndGames(View view){
        if(!ignoreclick){

            ignoreclick = true;

            if(null!=backgroundmusic&&backgroundmusic.isPlaying())
                backgroundmusic.stop();

            if(soundon)
                playTap();

            Intent i = new Intent(this, FunActivitiesAndGames.class);
            startActivity(i);
            finish();
        }

    }

    public void goToFeedback(){
        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();

        Intent i = new Intent(this, Feedback.class);
        startActivity(i);
    }

    public void goToFAQ(){

        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();

        Intent i = new Intent(this, FAQ.class);
        startActivity(i);
    }

    public void goToSettings(){

        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();

        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(null!=backgroundmusic&&backgroundmusic.isPlaying())
            backgroundmusic.stop();
    }
}