package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.leapworld.kidsvio.databinding.ActivityNurseryRhymesGridViewBinding;
import com.leapworld.kidsvio.databinding.ActivityRewardsBinding;

import java.util.List;

public class Rewards extends AppCompatActivity {

    DBHandler handler;
    List<Stickers> allrewards;

    int[] stickerids;
    int[] stickers;
    String[] stickernames;
    int[] rewardids;

    ActivityRewardsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        binding = ActivityRewardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new DBHandler(this, null, null, 1);

        getAllRewards();

        RewardsGridAdapter gridAdapter = new RewardsGridAdapter(Rewards.this, stickerids, stickers, stickernames, rewardids);
        binding.gridView.setAdapter(gridAdapter);

    }

    private void getAllRewards(){

        allrewards = handler.getAllStickers();

        stickerids = new int[allrewards.size()];
        stickers = new int[allrewards.size()];
        stickernames = new String[allrewards.size()];
        rewardids = new int[allrewards.size()];

        for (int i=0; i<allrewards.size(); i++){
            stickerids[i] = allrewards.get(i).get_id();
            stickers[i] = allrewards.get(i).get_sticker();
            stickernames[i] = allrewards.get(i).get_stickername();
            rewardids[i] = allrewards.get(i).get_rewardid();
        }



    }

}