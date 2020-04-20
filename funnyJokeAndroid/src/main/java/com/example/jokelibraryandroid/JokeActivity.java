package com.example.jokelibraryandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.jokelibraryandroid.databinding.ActivityJokeBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity shows a joke provided via intent and
 * allows to share it
 */

public class JokeActivity extends AppCompatActivity {


    public static final String JOKE_KEY = "JOKE";

    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        ActivityJokeBinding mBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_joke);

        Intent intent = getIntent();

        if (intent != null) {
            mJoke = intent.getStringExtra(JOKE_KEY);
            if(mJoke != null){
                mBinding.jokeText.setText(mJoke);

                mBinding.shareImageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        shareJoke();
                    }
                });
            }
        }
    }

    /**
     * Method used to share the joke using a sharingIntent
     */
    public void shareJoke(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, mJoke);
        startActivity(Intent.createChooser(sharingIntent, "Share joke"));
    }
}
