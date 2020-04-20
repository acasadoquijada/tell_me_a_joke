package com.udacity.gradle.builditbigger;


import androidx.test.espresso.IdlingResource;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

/**
 * Resource needed it to test the ads in Espresso
 *
 * Obtained from:
 * https://github.com/firebase/quickstart-android/blob/master/admob/app/src/androidTest/
 * java/com/google/samples/quickstart/admobexample/AdViewIdlingResource.java
 *
 * This file is under Apache License 2.0 as set in the github repository
 * https://github.com/firebase/quickstart-android/blob/master/LICENSE
 */

public class AdViewIdlingResource implements IdlingResource {

    private AdView mAdView;
    private AdListener mAdListener;
    private ResourceCallback mResourceCallback;
    private boolean mIsLoadingAd = false;

    public AdViewIdlingResource(AdView adView) {
        if (adView == null) {
            throw new IllegalArgumentException(
                    "Can't initialize AdViewIdlingResource with null AdView.");
        }

        this.mAdView = adView;
        this.mAdListener = new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                transitionToIdle();
            }

            @Override
            public void onAdLoaded() {
                transitionToIdle();
            }
        };

        mAdView.setAdListener(mAdListener);
    }

    @Override
    public String getName() {
        return "AdViewIdlingResource:" + mAdView;
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = !mIsLoadingAd;
        if (idle) {
            transitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.mResourceCallback = callback;
    }

    public void setIsLoadingAd(boolean isLoadingAd) {
        this.mIsLoadingAd = isLoadingAd;
    }

    public void transitionToIdle() {
        mIsLoadingAd = false;
        if (mResourceCallback != null) {
            mResourceCallback.onTransitionToIdle();
        }
    }
}

