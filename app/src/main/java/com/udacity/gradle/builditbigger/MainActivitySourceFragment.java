package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokelibraryandroid.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivitySourceFragment extends Fragment {

    public MainActivitySourceFragment() {

    }

    /**
     * Set a onClickListener to the jokeButton to obtain a joke and launch the JokeActivity from
     * the funnyJokeAndroid library
     *
     * @param inflater LayoutInflater object
     * @param container ViewGroup object
     * @param savedInstanceState Bundle object
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_source, container, false);

        root.findViewById(R.id.jokeButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new GetJokeAndLaunchJokeActivity(getContext()).execute();

            }
        });

        return root;
    }
}
