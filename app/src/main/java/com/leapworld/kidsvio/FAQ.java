package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

public class FAQ extends AppCompatActivity {

    //int[] faqbuttons = new int[]{R.id.textFAQ1, R.id.textFAQ2, R.id.textFAQ3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_faq);



        WebView FAQViewer = (WebView) findViewById(R.id.faqviewer);

        FAQViewer.getSettings().setJavaScriptEnabled(true);
        FAQViewer.setBackgroundColor(Color.TRANSPARENT);
        FAQViewer.loadUrl("file:///android_asset/faq.html");


    }


    public void onBackPressed() {

        Intent i = new Intent(this, Categories.class);
        startActivity(i);
    }

    /*public void showFAQ(View view){

        int clickedcount = faqbuttons.length;

        TextView clickedtextview = (TextView) view;
        String clickedtext = clickedtextview.getText().toString();

        for (int i=0;i<faqbuttons.length;i++){

            TextView buttontextview = (TextView) findViewById(faqbuttons[i]);
            String buttontext = buttontextview.getText().toString();

            if(clickedtext.equalsIgnoreCase(buttontext)){
                clickedcount = i;
                break;
            }
        }

        FAQPopUp faqPopUp = new FAQPopUp();
        faqPopUp.showPopupWindow(view, clickedcount);

    }*/

}