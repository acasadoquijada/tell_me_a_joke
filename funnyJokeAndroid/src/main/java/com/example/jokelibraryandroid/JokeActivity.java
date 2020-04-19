package com.example.jokelibraryandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "JOKE";

    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();

        if (intent != null) {
            mJoke = intent.getStringExtra(JOKE_KEY);
        }

        TextView jokeTextView = findViewById(R.id.jokeText);

        jokeTextView.setText(mJoke);

        ImageView jokeImageView = findViewById(R.id.shareImageView);
        jokeImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shareJoke();
            }
        });
    }


    // This could be a class static (?)
    public void shareJoke(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, mJoke);
        startActivity(Intent.createChooser(sharingIntent, "Share joke"));
    }
}
