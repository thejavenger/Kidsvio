package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AudioPlayer extends AppCompatActivity {

    String[] playlistnames;
    int[] playlistids;
    int position;

    String audio;
    String audioname;
    int audioid;

    ConstraintLayout audioplayer_bg;

    TextView audiotype;
    TextView audiotitle;
    ImageView pauseplaybutton;
    ImageView previousbutton;
    ImageView nextbutton;
    SeekBar seekBar;
    TextView textduration;
    TextView textposition;

    int bg_lullabies;
    int bg_moralstories;

    String timeduration;
    String timecurrentposition;

    MediaPlayer player;

    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_audio_player);

        Bundle bundle = this.getIntent().getExtras();

        audio = bundle.getString("audiotype");
        playlistnames = bundle.getStringArray("audionames");
        playlistids = bundle.getIntArray("audioids");
        position = bundle.getInt("position");

        audioname = playlistnames[position];
        audioid = playlistids[position];

        handler = new Handler();

        audioplayer_bg = (ConstraintLayout) findViewById(R.id.bgAudioPlayer);

        audiotype = (TextView) findViewById(R.id.textAudioType);
        audiotitle = (TextView) findViewById(R.id.audioTitle);
        pauseplaybutton = (ImageView) findViewById(R.id.imgPausePlay);
        previousbutton = (ImageView) findViewById(R.id.imgPrev);
        nextbutton = (ImageView) findViewById(R.id.imgNext);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textduration = (TextView) findViewById(R.id.textDuration);
        textposition = (TextView) findViewById(R.id.textPosition);

        bg_lullabies = R.drawable.bg_lullabies;
        bg_moralstories = R.drawable.bg_shortstories;


        if (audio.equalsIgnoreCase("Moral Stories")) {
            audioplayer_bg.setBackgroundResource(bg_moralstories);
        } else if (audio.equalsIgnoreCase("Lullabies")) {
            audioplayer_bg.setBackgroundResource(bg_lullabies);
        }


        audiotype.setText(audio);
        audiotitle.setText(audioname);
        player = MediaPlayer.create(this, audioid);

        seekBar.setMax(player.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    player.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playAudio();
    }

    public void pressPausePlayAudio(View view) {
        if (null != player && player.isPlaying()) {

            pauseAudio();
        } else {
            playAudio();
        }

    }

    public void previousAudio(View view) {
        if (previousbutton.isEnabled()) {
            position--;

            if (null != player && player.isPlaying()) {
                player.stop();
                player = null;
            }

            audioname = playlistnames[position];
            audioid = playlistids[position];

            audiotitle.setText(audioname);
            player = MediaPlayer.create(this, audioid);

            seekBar.setMax(player.getDuration());

            playAudio();
        }


    }


    public void nextAudio(View view) {
        if (nextbutton.isEnabled()) {
            position++;

            if (null != player && player.isPlaying()) {
                player.stop();
                player = null;
            }


            audioname = playlistnames[position];
            audioid = playlistids[position];

            audiotitle.setText(audioname);
            player = MediaPlayer.create(this, audioid);

            seekBar.setMax(player.getDuration());

            playAudio();
        }
    }

    public void rewindAudio(View view) {

        int SUB_TIME = 5000;

        int audioposition = player.getCurrentPosition();

        if (audioposition - SUB_TIME > 0) {
            player.seekTo(audioposition - SUB_TIME);
        }
    }

    public void fastForwardAudio(View view) {

        int ADD_TIME = 5000;

        int audioposition = player.getCurrentPosition();
        int audioduration = player.getDuration();


        if (audioposition + ADD_TIME < audioduration) {
            player.seekTo(audioposition + ADD_TIME);
        }
    }

    public void handlePreviousNext() {

        int playlistsize = playlistids.length;

        previousbutton.setEnabled(false);
        nextbutton.setEnabled(false);

        if (position > 0) {
            previousbutton.setEnabled(true);
        }

        if (position < playlistsize - 1) {
            nextbutton.setEnabled(true);
        }


    }

    public void playAudio() {
        handlePreviousNext();

        pauseplaybutton.setImageResource(R.drawable.pause_white_128);
        player.start();

        UpdateSeekBar updater = new UpdateSeekBar();
        handler.post(updater);

    }


    public void pauseAudio() {
        pauseplaybutton.setImageResource(R.drawable.play_white_128);
        player.pause();
    }

    public String millisecondsToString(int ms) {
        String time = "";

        time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms), TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));

        return time;
    }

    public class UpdateSeekBar implements Runnable {

        @Override
        public void run() {

            textposition.setText(millisecondsToString(player.getCurrentPosition()));
            textduration.setText(millisecondsToString(player.getDuration()));

            seekBar.setProgress(player.getCurrentPosition());
            handler.postDelayed(this, 100);
        }
    }

    public void onBackPressed() {

        if (null != player && player.isPlaying())
            player.stop();

        Intent i = new Intent(this, ListenAudioClips.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != player && player.isPlaying())
            pauseAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != player && player.isPlaying())
            pauseAudio();
    }
}