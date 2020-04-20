package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragmentFree extends Fragment {

    private static final String TAG = MainActivityFragmentFree.class.getSimpleName();

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragmentFree() {
    }

    /**
     * Needed for testing
     * @return the adview
     */
    @VisibleForTesting
    public AdView getAdView() {
        return mAdView;
    }

    /**
     * Setup all the ads
     * @param inflater LayoutInflater object
     * @param container ViewGroup object
     * @param savedInstanceState Bundle object
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_free, container, false);

        setUpInterstitialAd();

        mAdView = (AdView) root.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        // Shows teh interstital ad when the button is pressed
        root.findViewById(R.id.jokeButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d(TAG, "The interstitial wasn't loaded yet.");
                }
            }
        });

        return root;
    }

    private void setUpInterstitialAd(){
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener(){
            // Once the add is closed the joke is requested and presented
            // Also loads a new add in case the user goes back to the mainActivity
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                new GetJokeAndLaunchJokeActivity(getContext()).execute();
            }
        });
    }
}
