package com.leapworld.kidsvio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RewardsGridAdapter extends BaseAdapter {

    Context context;
    int[] stickerids;
    int[] stickers;
    String[] stickernames;
    int[] rewardids;

    LayoutInflater inflater;

    public RewardsGridAdapter(Context context, int[] stickerids, int[] stickers, String[] stickernames, int[] rewardids) {
        this.context = context;
        this.stickerids = stickerids;
        this.stickers = stickers;
        this.stickernames = stickernames;
        this.rewardids = rewardids;
    }


    @Override
    public int getCount() {
        return stickers.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater==null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            convertView = inflater.inflate(R.layout.rewards_grid_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);

        imageView.setImageResource(stickers[position]);
        textView.setText(stickernames[position]);

        return convertView;
    }
}
