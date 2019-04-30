package com.example.lulu.assignment;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer extends Service {
    MediaPlayer player;
    public MusicPlayer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int position = intent.getIntExtra("position", -1);

        List<Integer> music = new ArrayList<>();
        music.add(R.raw.gbona);
        music.add(R.raw.flames);
        music.add(R.raw.alexiane);
        music.add(R.raw.home);
        music.add(R.raw.like);
        music.add(R.raw.ojuelegba);

        if (player != null){
            player.stop();
        }
        if (position != -1){
            player = MediaPlayer.create(this, music.get(position));
            player.start();
            player.setLooping(true);
            player.setVolume(100,100);
        }

        Toast.makeText(this, "Music Started", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null){
            player.stop();
            Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show();
        }
    }
}
