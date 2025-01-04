package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    String[] features = new String[]{"Audio Clips", "Nursery Rhymes", "Fun Activities and Games"};
    int[] tappers = new int[]{R.id.audiocliptapper, R.id.nurseryrhymestapper, R.id.funactivitiestapper};
    int[] tapperimages = new int[]{R.drawable.btn_audioclips, R.drawable.btn_nurseryrhymes, R.drawable.btn_funactivities};
    int[] notifiers = new int[]{R.drawable.btn_audioclips_tapped, R.drawable.btn_nurseryrhymes_tapped, R.drawable.btn_funactivities_tapped};

    String mostlikedfeature = "--";
    boolean acceptfeedback = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_feedback);
    }

    public void imageTapped(View view){
        int clickedcount = tappers.length;

        ImageView tappedimage = (ImageView) view;

        for (int i=0;i<tappers.length;i++){

            ImageView tapper = (ImageView) findViewById(tappers[i]);

            if(tappedimage==tapper){
                clickedcount = i;
                tappedimage.setImageResource(notifiers[i]);
                mostlikedfeature = features[i];
                acceptfeedback = true;
            }else{
                tapper.setImageResource(tapperimages[i]);
            }
        }

    }

    public void submitFeedback(View view){

        if(acceptfeedback){
            EditText feedbacktext = (EditText) findViewById(R.id.textfeedback);
            ImageView feedbackbutton = (ImageView) findViewById(R.id.btnSubmit);

            Intent i = new Intent(Intent.ACTION_SENDTO);

            i.setType("message/html");
            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kidsvio.feed@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Feedback to Kidsvio");
            i.putExtra(Intent.EXTRA_TEXT, "Most liked feature: " + mostlikedfeature + "\n\n" + "Feedback: \n" + feedbacktext.getText());

            try {

                startActivity(Intent.createChooser(i, "Please select Email"));

            }catch (ActivityNotFoundException ex){

                Toast.makeText(Feedback.this, "There are no Email Clients", Toast.LENGTH_SHORT);
            }
        }
    }


    public void onBackPressed() {

        Intent i = new Intent(this, Categories.class);
        startActivity(i);
    }

}