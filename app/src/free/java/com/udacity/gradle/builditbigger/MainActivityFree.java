package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdView;


/**
 * This class is similar to MainActivitySource, but it loads a MainActivityFragmentFree that contains
 * ads as this is used for the free Flavor
 */

public class MainActivityFree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_source);

        if(savedInstanceState == null){
            populateUI();
        }
    }

    private void populateUI(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        MainActivityFragmentFree mainActivityFragmentFree = new MainActivityFragmentFree();

        fragmentManager.beginTransaction().add(R.id.fragment, mainActivityFragmentFree).commit();
    }
}
