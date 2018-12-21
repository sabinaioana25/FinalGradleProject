package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.jokelibrary.Jokes;
import com.example.android.jokesandroidlibrary.LibJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity implements
        EndpointAsyncTask.Callback {

    private static String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String JOKE = "joke";
    Jokes jokes = new Jokes();
    private static InterstitialAd mInterstitialAd;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Intent myIntent = new Intent(getApplicationContext(), LibJokeActivity.class);
                myIntent.putExtra(JOKE, Jokes.tellJoke());
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startJokeActivity(View view) {

        if (mInterstitialAd.isLoaded()) {

            mInterstitialAd.show();
        } else {
            Log.e(LOG_TAG, "The ad failed to load");
            Toast toast = Toast.makeText(this, this.getString(R.string.toast_text), Toast
                    .LENGTH_SHORT);
            toast.show();
            new EndpointAsyncTask(this).execute(getApplicationContext());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onFinished(String result) {
        Intent myIntent = new Intent(this, LibJokeActivity.class);
        myIntent.putExtra(JOKE, result);
        startActivity(myIntent);
    }
}