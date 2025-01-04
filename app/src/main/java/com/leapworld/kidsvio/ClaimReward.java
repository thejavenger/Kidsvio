package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ClaimReward extends AppCompatActivity {

    int[] stickers =  new int[]{
            R.drawable.ag_1, R.drawable.ag_2, R.drawable.ag_3, R.drawable.ag_4, R.drawable.ag_5,
            R.drawable.ag_6, R.drawable.ag_7, R.drawable.ag_8, R.drawable.ag_9,
            R.drawable.mn_1, R.drawable.mn_2, R.drawable.mn_3, R.drawable.mn_4, R.drawable.mn_5,
            R.drawable.mn_6, R.drawable.mn_7, R.drawable.mn_8, R.drawable.mn_9, R.drawable.mn_10,
            R.drawable.sb_1, R.drawable.sb_2, R.drawable.sb_3, R.drawable.sb_4, R.drawable.sb_5,
            R.drawable.sb_6, R.drawable.sb_7, R.drawable.sb_8, R.drawable.sb_9, R.drawable.sb_10,
            R.drawable.stc_1, R.drawable.stc_2, R.drawable.stc_3, R.drawable.stc_4, R.drawable.stc_5,
            R.drawable.stc_6, R.drawable.stc_7, R.drawable.stc_8, R.drawable.stc_9, R.drawable.stc_10
    };

    String[] stickername = new String[]{
            "Thanos", "Hawkeye", "Captain America", "Ragnarok Hulk", "The Wasp",
            "Scarlet Witch", "Hella", "Mad Titan", "Punisher",
            "Stuart", "Peeking Minion", "Guitar Minion", "Chef Minion", "Minion with Teddy Bear",
            "Confused Minion", "Minion on Outfit", "Tim", "Plumber Minion", "Wigged Minion",
            "Mr. Krabs", "Cheerful Spongebob", "Plankton", "Patrick", "Mrs. Puff & Spongebob",
            "Spongebob with a Hat", "Ready Spongebob", "Sad Spongebob", "Santa Sponge", "Needle 7 Sponge",
            "Stitch 1", "Stitch 2", "Stitch 3", "Stitch 4", "Stitch 5",
            "Stitch 6", "Stitch 7", "Lilo", "Stitch inlove", "Lilo & Stitch"
    };

    int stickercount = 0;

    DBHandler handler;
    Stickers reward;

    int newsticker;
    String newstickername;

    int stickertouse;
    String stickernametouse;
    int rewardid;

    TextView textstickername;
    ImageView imagesticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_claim_reward);

        handler = new DBHandler(this, null, null, 1);

        textstickername = (TextView) findViewById(R.id.textStickerName);
        imagesticker = (ImageView) findViewById(R.id.imgSticker);

        stickercount = stickers.length;

        shuffleStickers();

    }

    public void shuffleStickers(){

        boolean stickercollected = false;

        do {
            Random rand = new Random();
            int newrand = rand.nextInt(stickercount);

            newsticker = stickers[newrand];
            newstickername = stickername[newrand];

            rewardid = newrand;

            if(handler.stickerExist(newsticker))
                stickercollected = true;
            else
                stickercollected = false;


        }while (stickercollected);

        stickertouse = newsticker;
        stickernametouse = newstickername;

        if(stickernametouse.equals(""))
            stickernametouse = "--";

        textstickername.setText(stickernametouse);
        imagesticker.setImageResource(stickertouse);

    }


    public void claimSticker(View view){

        reward = new Stickers();

        reward.set_sticker(stickertouse);
        reward.set_stickername(stickernametouse);
        reward.set_rewardid(rewardid);

        handler.addStickers(reward);

        Intent i = new Intent(this, FunActivitiesAndGames.class);
        startActivity(i);

    }

    public void onBackPressed() {

        Intent i = new Intent(this, FunActivitiesAndGames.class);
        startActivity(i);
    }
}