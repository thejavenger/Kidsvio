package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.github.ybq.android.spinkit.SpinKitView;

public class CustomLoadingScreen extends AppCompatActivity {

    private SpinKitView spinkit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_custom_loading_screen);

        spinkit1 = findViewById(R.id.spin_kit1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToCategories();
            }

        }, 3000);
    }

    public void goToCategories(){
        Intent i = new Intent(this, Categories.class);
        startActivity(i);
    }

}