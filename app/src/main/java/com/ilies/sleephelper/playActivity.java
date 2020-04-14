package com.ilies.sleephelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class playActivity extends AppCompatActivity {
    //---
    private CountDownTimer timerobj;
    //---

    ImageView playimg,songimg;
    TextView playtxt,timertxt,titletxt;
    Animation rotation;
    public static long timeTostop ;
    boolean timerisRunning = false;
    private MediaPlayer song;


    int selected;
    public int [] images ={
            R.drawable.img0,R.drawable.img1,R.drawable.img2,
            R.drawable.img3,R.drawable.img4,R.drawable.img5,
            R.drawable.img6,R.drawable.img7
    };
    public int [] songs = {
            R.raw.audio0,R.raw.audio1,R.raw.audio2,
            R.raw.audio3,R.raw.audio4,R.raw.audio5,
            R.raw.audio6,R.raw.audio7

    };
    public String [] titles = {
            "Piano Music & Rain Sounds","Night Nature Sounds","Rain and Thunder Sounds",
            "Rain on window"," Ocean Waves","Starship Sleeping Quarters",
            "Farm Ambient Sounds With Birds","Peaceful Piano & Soft Rain"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playimg  = findViewById(R.id.playimg);
        songimg  = findViewById(R.id.songimg);
        playtxt  = findViewById(R.id.playtxt);
        timertxt  = findViewById(R.id.timerText);
        titletxt  = findViewById(R.id.titletxt);
        //Animation
        rotation = AnimationUtils.loadAnimation(playActivity.this, R.anim.rotate);
        rotation.setFillAfter(true);

        // get Selected sound :
        Intent intent = getIntent();
        selected = intent.getIntExtra("selected",0);



        //seting imge and song file acording to the selected item :
        songimg.setImageResource(images[selected]);

        titletxt.setText(""+titles[selected]);

        timeTostop = 0 ;






    }

    public void timer(View view) {

        CustomDialogClasstimer cdd=new CustomDialogClasstimer(playActivity.this);
        cdd.show();

        stopMedia();

    }
    public void checkStat()
    {
        if (playtxt.getText().equals("play"))
        {
            startMedia();

        }else {

            stopMedia();
        }
    }



    public void playbtn(View view) {
        checkStat();


    }

    public void notepad(View view) {

        Intent myIntent = new Intent(playActivity.this, notepad.class);
        playActivity.this.startActivity(myIntent);
    }

    public void home(View view) {

        stopMedia();
        Intent myIntent2 = new Intent(playActivity.this, MainActivity.class);
        playActivity.this.startActivity(myIntent2);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        stopMedia();

        Intent myIntent2 = new Intent(playActivity.this, MainActivity.class);
        playActivity.this.startActivity(myIntent2);
        this.finish();

    }

    private void stopMedia() {

        if (timerisRunning){
            timerobj.cancel();
            timertxt.setText("Timer");
        }
        playimg.setImageResource(R.drawable.playstyle1);
        songimg.clearAnimation();
        playtxt.setText("play");
        try {
            song.reset();
            song.prepare();
            song.stop();
            song.release();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void startMedia()
    {
        if(timeTostop >0)
        {
            startTimer(timeTostop);
        }
        playtxt.setText("pause");
        playimg.setImageResource(R.drawable.pause);
        songimg.startAnimation(rotation);
        //Song play loading :
        song = MediaPlayer.create(playActivity.this,songs[selected]);
        song.setLooping(true);
        song.start();
    }

    public void startTimer(long time)
    {

        timerobj= new CountDownTimer(time,1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timertxt.setText(""+String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            timerisRunning = true;
            }

            public void onFinish() {
                timertxt.setText("done!");
                timerisRunning = false;
                stopMedia();
            }

        }.start();
    }
}
