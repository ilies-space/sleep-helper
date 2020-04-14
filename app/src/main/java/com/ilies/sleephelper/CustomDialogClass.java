package com.ilies.sleephelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public LinearLayout feedback,notepad,rating;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_exit);
        no = (Button) findViewById(R.id.btn_close);
        notepad = (LinearLayout) findViewById(R.id.notepad);
        feedback = (LinearLayout) findViewById(R.id.feedback);
        rating = (LinearLayout) findViewById(R.id.rate);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        notepad.setOnClickListener(this);
        feedback.setOnClickListener(this);
        rating.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                c.finish();
                break;
            case R.id.btn_close:
                dismiss();
                break;

            case R.id.notepad:
                //open Notepad :
                Intent notepad = new Intent(c, notepad.class);
                c.startActivity(notepad);
                break;

            case R.id.rate:
                //open Notepad :
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    c.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    c.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }
                break;

            case R.id.feedback:
                //open Notepad :
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "ilyasdzair1@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "sleep helper app feedback");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                c.startActivity(Intent.createChooser(intent, ""));
                break;
            default:
                break;
        }
        dismiss();
    }
}