package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.leapworld.kidsvio.databinding.ActivityNurseryRhymesGridViewBinding;

public class NurseryRhymesGridView extends AppCompatActivity {

    boolean ignoreclick = false;

    ActivityNurseryRhymesGridViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        binding = ActivityNurseryRhymesGridViewBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        final String[] itemName = {"Five little Monkeys", "Humpty Dumpty", "Incy Wincy Spider",
                "Old McDonald", "Pitter Patter", "Ring a Roses",
                "Row Boat", "Twinkle Little Star", "Way Lady Rides", "Wheels On The Bus"};

        final String[] itemFilename = {"vid_fivelittlemonkeys", "vid_humptydumpty", "vid_incywincyspider",
                "vid_oldmcdonald", "vid_pitterpatter", "vid_ringaroses",
                "vid_rowboat", "vid_twinklelittlestar", "vid_wayladyrides",  "vid_wheelsonthebus"};


        NurseryRhymesGridAdapter gridAdapter = new NurseryRhymesGridAdapter(NurseryRhymesGridView.this, itemName, itemFilename);
        binding.gridView.setAdapter(gridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!ignoreclick) {
                    ignoreclick = true;
                    String fulluri = "android.resource://" + getPackageName() + "/raw/" + itemFilename[position];

                    Intent i = new Intent(NurseryRhymesGridView.this, NurseryRhymes.class);
                    i.putExtra("uri", fulluri);
                    startActivity(i);

                    //Toast.makeText(NurseryRhymesGridView.this, "You clicked on " + itemName[position], Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void goBackToCategories(View view){
        Intent i = new Intent(this, Categories.class);
        startActivity(i);
        finish();
    }


    public void onBackPressed() {
        Intent i = new Intent(this, Categories.class);
        startActivity(i);
    }


}