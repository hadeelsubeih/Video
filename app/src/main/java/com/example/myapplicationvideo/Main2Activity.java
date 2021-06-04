package com.example.myapplicationvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class Main2Activity extends AppCompatActivity {
    PlayerView playerView;
    SimpleExoPlayer player;
    private int CurrentWindow = 0;
    private boolean PlayWhenReady = true;
    private long playerff = 0;
    String vidooURL = "https://www.youtube.com/watch?v=bsPsspWxAQ0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        playerView = findViewById(R.id.vidoView2);


    }

    public void initVideo() {
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri = Uri.parse(vidooURL);
        DataSource.Factory DataSource = new DefaultDataSourceFactory(this, "exoplayer-codelab");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(DataSource).createMediaSource(uri);
        player.setPlayWhenReady(PlayWhenReady);
        player.seekTo(CurrentWindow, playerff);
        player.prepare(mediaSource, false, false);
    }

    public void releseVideo() {
        if (player != null) {
            PlayWhenReady = player.getPlayWhenReady();
            playerff = player.getContentPosition();
            CurrentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVideo();


    }

    @Override
    protected void onStop() {
        super.onStop();
        releseVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releseVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            initVideo();

        }

    }
}
