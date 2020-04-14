package com.ilies.sleephelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 4/14/20 ADD 10 songs .. and 1 top ranked 
        // TODO: 4/14/20 compress all images



    }

    public void navbar(View view) {

        CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
        cdd.show();

    }

    public void play(View view) {
        Intent myIntent = new Intent(MainActivity.this, playActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.finish();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void song0(View view) {
        sendData(0);
    }
    public void song1(View view) {
        sendData(1);
    }
    public void song2(View view) {
        sendData(2);
    }
    public void song3(View view) {
        sendData(3);
    }
    public void song4(View view) {
        sendData(4);
    }
    public void song5(View view) {
        sendData(5);
    }
    public void song6(View view) {
        sendData(6);
    }
    public void song7(View view) {
        sendData(7);
    }




    public void sendData(int x)
    {
        Intent myIntent = new Intent(MainActivity.this, playActivity.class);
        myIntent.putExtra("selected",x);
        MainActivity.this.startActivity(myIntent);
    }


    public void nighmood(View view) {
        Toast.makeText(this, "Night mode future will be available on the next update , Stay toned", Toast.LENGTH_LONG).show();
    }
}
