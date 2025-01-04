package com.leapworld.kidsvio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class CategoriesPopUp {


    MediaPlayer tap;
    //DBHandler handler;

    Context context;
    boolean musicon;
    boolean soundon;

    boolean ignoreclick = false;

    public CategoriesPopUp(Context context, boolean musicon, boolean soundon) {
        this.musicon = musicon;
        this.soundon = soundon;
        this.context = context;
    }

    /*public void getSettings(){
        handler = new DBHandler(null, null, null, 1);

        Setting musicsetting = handler.getMusicSetting();
        Setting soundsetting = handler.getSoundSetting();

        musicon = musicsetting.get_intvalue()>0;
        soundon = soundsetting.get_intvalue()>0;
    }*/

    public void playTap(){
        if(tap!=null){
            tap.release();;
            tap = null;
        }

        tap = MediaPlayer.create(context, R.raw.aud_plasticbubble);
        tap.start();
    }

    public void showPopupWindow(View view){

        //getSettings();

        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.categories_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        ImageButton imageFeedback = popupView.findViewById(R.id.imageFeedback);
        ImageButton imageFAQ = popupView.findViewById(R.id.imageFAQ);
        ImageButton imageSettings = popupView.findViewById(R.id.imageSettings);

        imageFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ignoreclick) {
                    ignoreclick = true;


                    if (soundon)
                        playTap();

                    Intent i = new Intent(context, Feedback.class);
                    context.startActivity(i);
                    ((Activity) context).finish();
                }
            }
        });

        imageFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ignoreclick) {
                    ignoreclick = true;
                    if (soundon)
                        playTap();

                    Intent i = new Intent(context, FAQ.class);
                    context.startActivity(i);
                    ((Activity) context).finish();
                }
            }
        });

        imageSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ignoreclick) {
                    ignoreclick = true;
                    if (soundon)
                        playTap();

                    Intent i = new Intent(context, Settings.class);
                    context.startActivity(i);
                    ((Activity) context).finish();
                }
            }
        });

    }

}
