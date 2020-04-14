package com.ilies.sleephelper;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import static com.ilies.sleephelper.playActivity.timeTostop;

public class CustomDialogClasstimer extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public LinearLayout choice1,choice2,choice3;
    public Button close ;

    public CustomDialogClasstimer(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_timer);

        close = (Button) findViewById(R.id.btn_close);
        choice1 = (LinearLayout) findViewById(R.id.choice1);
        choice2 = (LinearLayout) findViewById(R.id.choice2);
        choice3 = (LinearLayout) findViewById(R.id.choice3);

        close.setOnClickListener(this);
        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.choice1:
                //
                //Toast.makeText(c, "15 min", Toast.LENGTH_SHORT).show();
                timeTostop= 900000;


                break;

            case R.id.choice2:
                //
                //Toast.makeText(c, "30 min", Toast.LENGTH_SHORT).show();
                timeTostop= 1800000;



                break;

            case R.id.choice3:
                //open Notepad :
                //Toast.makeText(c, "1 h", Toast.LENGTH_SHORT).show();
                timeTostop= 3600000;

                break;

            case R.id.btn_close:
                //
                dismiss();
                break;

            default:
                break;
        }
        dismiss();
    }
}