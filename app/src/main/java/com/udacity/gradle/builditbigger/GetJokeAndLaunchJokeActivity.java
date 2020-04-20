package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.jokelibraryandroid.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * This AsyncTask class requests a joke to the GCE (backend module) and then
 * launches a JokeActivity with the result
 *
 * This is an independent class in order to be used in both flavors
 *
 * This class is tested in:
 * - MainActivitySourceIntentTest (clickOnButton_CreatesCorrectIntentInfo)
 * - MainActivityFreeIntentTest (clickOnButton_CreatesCorrectIntentInfo)
 */

public class GetJokeAndLaunchJokeActivity extends AsyncTask<Void, Void, String> {

    private MyApi myApiService = null;
    private Context context;
    private ProgressDialog loadingDialog;

    public GetJokeAndLaunchJokeActivity(Context context){
        this.context = context;
    }
    /**
     * Shows dialog while loading the joke
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingDialog = new ProgressDialog(context);
        loadingDialog.setMessage("Loading a funny joke!");
        loadingDialog.setIndeterminate(false);
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadingDialog.setCancelable(false);
        loadingDialog.show();
    }

    /**
     * Requests a Joke to the GCE (backend module)
     * The ip set in .setRootUrl must be the one of your machine
     * @return a joke
     */
    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.114:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * This method dismiss the dialog and launches the JokeActivity
     * @param result String containing the joke
     */
    @Override
    protected void onPostExecute(String result) {

        loadingDialog.dismiss();

        Intent intent = new Intent(context, JokeActivity.class);

        intent.putExtra(JokeActivity.JOKE_KEY,result);

        context.startActivity(intent);
    }
}
