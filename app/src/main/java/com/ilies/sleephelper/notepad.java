package com.ilies.sleephelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class notepad extends AppCompatActivity {
    EditText uinput ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        uinput = findViewById(R.id.editText);
        updateNote("Note");

    }

    public void save(View view) {

        savecontent();
        Toast.makeText(this, "Saved !", Toast.LENGTH_SHORT).show();

    }

    public void savecontent()
    {
        String content = uinput.getText().toString();
        String name = "Note"; // You can create a new EditText for getting name
        // Using SharedPreferences (the simple way)
        SharedPreferences sp = this.getApplicationContext().getSharedPreferences("notes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name, content);
        editor.apply();
        updateNote("Note");

    }

    private String getNoteContent(String noteName) {
        SharedPreferences sp = this.getApplicationContext().getSharedPreferences("notes", Context.MODE_PRIVATE);
        return sp.getString(noteName, "Empty note !");

    }

    public void updateNote(String Note)
    {
        uinput.setText(getNoteContent(Note));
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        savecontent();
        this.finish();

    }


}
