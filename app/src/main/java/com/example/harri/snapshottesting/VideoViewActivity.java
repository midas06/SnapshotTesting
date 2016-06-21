package com.example.harri.snapshottesting;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

    VideoView videoView;
    MediaController mediaControls;
    int position = 0;
    ImageView btnHome, btnTimelapse, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = (VideoView) findViewById(R.id.videoView);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnHome = (ImageView) findViewById(R.id.btnHome);
        btnTimelapse = (ImageView) findViewById(R.id.btnTimelapse);

        if (mediaControls == null) {
            mediaControls = new MediaController(VideoViewActivity.this);
        }

        try {
            videoView.setMediaController(mediaControls);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.glaciertimelapse;
            videoView.setVideoPath(path);
        } catch(Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                } else {
                    videoView.pause();
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_Home();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        videoView.seekTo(position);
    }


    public void onClick_Home() {
        Intent intent = new Intent(VideoViewActivity.this, LayoutActivity.class);
        startActivity(intent);
    }

    public void onClick_Timelapse() {
        Intent intent = new Intent(VideoViewActivity.this, VideoViewActivity.class);
        startActivity(intent);
    }

    public void onClick_Back() {
        finish();
    }

}
