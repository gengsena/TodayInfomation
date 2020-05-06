package com.gs.newstoday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by DELL on 2020/4/4.
 */

public class SplashActivity extends AppCompatActivity {

    private FullScreenVideoView mVideoView;
    private TextView tv_splash;
    CustomCountDownTimer timer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv_splash = (TextView) findViewById(R.id.tv_splash);
        tv_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mVideoView = (FullScreenVideoView) findViewById(R.id.vv_play);
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

       timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                tv_splash.setText(time+"秒");
            }

            @Override
            public void onFinish() {
                tv_splash.setText("跳过");
            }
        });
        timer.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancle();
    }
}
