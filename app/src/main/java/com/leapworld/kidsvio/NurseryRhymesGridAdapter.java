package com.leapworld.kidsvio;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NurseryRhymesGridAdapter extends BaseAdapter {

    Context context;
    String[] itemName;
    String[] itemFilename;

    LayoutInflater inflater;

    public NurseryRhymesGridAdapter(Context context, String[] itemName, String[] itemFilename) {
        this.context = context;
        this.itemName = itemName;
        this.itemFilename = itemFilename;
    }

    @Override
    public int getCount() {
        return itemName.length;
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

        final int[] thumbids = {R.drawable.thumb_fivelittlemonkeys, R.drawable.thumb_humpydumpy, R.drawable.thumb_incywincy,
                R.drawable.thumb_oldmcdonald, R.drawable.thumb_pitterpatter, R.drawable.thumb_ringaroses,
                R.drawable.thumb_rowtheboat, R.drawable.thumb_twinkletwinklelittlestar, R.drawable.thumb_waytheladyrides, R.drawable.thumb_wheelsonthebus};

        if(inflater==null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null){
            convertView = inflater.inflate(R.layout.video_grid_item, null);
        }

        Uri videoURI = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + itemFilename[position]);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(context, videoURI);
        Bitmap thumb = retriever.getFrameAtTime(90000, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);


        ImageView imageView = convertView.findViewById(R.id.grid_image);
        //TextView textView = convertView.findViewById(R.id.item_name);

        imageView.setImageResource(thumbids[position]);
        //imageView.setImageBitmap(thumb);
        //textView.setText(itemName[position]);


        return convertView;
    }
}
