package com.leapworld.kidsvio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AudioList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_audio_list);

        final String[] moralStoriesName = {"Akbar and the Half Reward", "I Like To Go Exploring", "Kate Crackernuts",
                "Mousey The Merchant", "Susu The Magic Mirror", "The Fur Feather",
                "The Great Hill", "The Island of Bum Bum Baloo", "The Journey of the Marmabill",
                "The Prince and His Three Fates", "The Stellarone", "The Talking Eggs",
                "The Two Brothers", "The Wereboy"};

        final int[] moralstoriesid = {R.raw.stories_akbarandthehalfreward, R.raw.stories_iliketogoexploring, R.raw.stories_katecrackernuts,
                R.raw.stories_mouseythemerchant, R.raw.stories_susuthemagicmirror, R.raw.stories_thefurfeather,
                R.raw.stories_thegreathill, R.raw.stories_theislandofbumbumbaloo, R.raw.stories_thejourneyofthemarmabill,
                R.raw.stories_theprinceandhisthreefates, R.raw.stories_thestellarone, R.raw.stories_thetalkingeggs,
                R.raw.stories_thetwobrothers, R.raw.stories_thewereboy};

        final String[] lullabiesName = {"Acoustic Dreams", "Bedtime Stories", "Beyond The Sky",
                "Dreamy Light", "Gentle Thunderstorm", "Here With Me",
                "Sea Of Tranquility", "Silverlight", "The Moon Light",
                "Warmth" };

        final int[] lullabiesid = {R.raw.lullabies_acousticdreams, R.raw.lullabies_bedtimestories, R.raw.lullabies_beyondthesky,
                R.raw.lullabies_dreamylight, R.raw.lullabies_gentlethunderstorm, R.raw.lullabies_herewithme,
                R.raw.lullabies_seaoftranquility, R.raw.lullabies_silverlight, R.raw.lullabies_themoonlight,
                R.raw.lullabies_warmth };

        final String[] listItems;
        final int[] listid;

        Bundle bundle = getIntent().getExtras();
        String cliptype = bundle.getString("audio");

        if(cliptype.equalsIgnoreCase("Moral Stories")){
            listItems = moralStoriesName;
            listid = moralstoriesid;
        }else if(cliptype.equalsIgnoreCase("Lullabies")){
            listItems = lullabiesName;
            listid = lullabiesid;
        }else{
            listItems = new String[0];
            listid = new int[0];
        }

        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundlesend = new Bundle();

                bundlesend.putString("audiotype", cliptype);
                bundlesend.putStringArray("audionames", listItems);
                bundlesend.putIntArray("audioids", listid);
                bundlesend.putInt("position", position);

                Intent intent = new Intent(AudioList.this, AudioPlayer.class);
                intent.putExtras(bundlesend);

                startActivity(intent);

            }
        });


    }

    public void onBackPressed() {
        Intent i = new Intent(this, ListenAudioClips.class);
        startActivity(i);
    }
}