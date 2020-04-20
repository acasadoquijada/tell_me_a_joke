package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;

/**
 * MainActivity without adds. Because of this is used in the paid Flavor by setting it in the
 * paid Android Manifest
 */

public class MainActivitySource extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_source);

        if(savedInstanceState == null){
            populateUI();
        }
    }

    /**
     * Creates a Fragment containing the logic to obtained the joke
     */
    private void populateUI(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        MainActivitySourceFragment mainActivitySource = new MainActivitySourceFragment();

        fragmentManager.beginTransaction().add(R.id.fragment, mainActivitySource).commit();
    }

}
