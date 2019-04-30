package com.example.lulu.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PlaySongs extends AppCompatActivity {

    private ListView mListView;
    private Context mContext = this;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_songs);

        mListView = (ListView) findViewById(R.id.songList);
        stopButton = (Button) findViewById(R.id.stopBtn);

        List<String> mList = new ArrayList<>();
        mList.add("Burna Boy - Gbona");
        mList.add("David Guetta and Sia - Flames");
        mList.add("Alexiane - A Million on My Soul");
        mList.add("Yellow Claw and Sophie Simmons - Home");
        mList.add("Reekado Banks and Tiwa Savage and Fiokee - Like");
        mList.add("Wizkid - Ojuelegba");

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mList);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serviceStart(position);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceStop();
            }
        });
    }

    public void serviceStart(int position){
        Intent mIntent = new Intent(this, MusicPlayer.class);
        mIntent.putExtra("position", position);
        startService(mIntent);
    }
    public void serviceStop(){
        Intent mIntent = new Intent(this, MusicPlayer.class);
        stopService(mIntent);
    }
}
