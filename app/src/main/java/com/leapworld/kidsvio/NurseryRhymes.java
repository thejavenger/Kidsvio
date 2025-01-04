package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class NurseryRhymes extends AppCompatActivity {

    private VideoView videoView;
    private ConstraintLayout mainlayout;
    private RelativeLayout videoLayout;
    private MediaController mediaController;

    String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_nursery_rhymes);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle bundle = getIntent().getExtras();
        videoPath = bundle.getString("uri");

        videoView = findViewById(R.id.videoView);
        mainlayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        videoLayout = (RelativeLayout) findViewById(R.id.mediaLayout);


        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController = new MediaController(NurseryRhymes.this);
                        mediaController.setPadding(0,0,0,0);


                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(mainlayout);
                        //mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

    }

    public void onBackPressed() {
        Intent i = new Intent(this, NurseryRhymesGridView.class);
        startActivity(i);
    }
}