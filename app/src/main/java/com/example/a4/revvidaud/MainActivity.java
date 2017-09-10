package com.example.a4.revvidaud;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    VideoView v;
    MediaController m;



    Button b1;
    Button b2;
    SeekBar s;
    MediaPlayer m1;
    AudioManager a;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v=(VideoView)findViewById(R.id.videoView);
        v.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.vid);
        m=new MediaController(this);

        m1=MediaPlayer.create(this,R.raw.aud);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);


        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        m1.start();
                    }
                }
        );

        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        m1.pause();
                    }
                }
        );

        a=(AudioManager)getSystemService(AUDIO_SERVICE);
        int max=a.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current=a.getStreamVolume(AudioManager.STREAM_MUSIC);

        s=(SeekBar)findViewById(R.id.seekBar);
        s.setProgress(current);
        s.setMax(max);
        s.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        a.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );






        v.setMediaController(m);
        m.setAnchorView(v);

        v.start();


    }
}
