package com.example.jokelibraryandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "JOKE";

    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();

        if(intent != null){
            mJoke = intent.getStringExtra(JOKE_KEY);
        }

        TextView jokeTextView = findViewById(R.id.jokeText);

        jokeTextView.setText(mJoke);
    }
}
