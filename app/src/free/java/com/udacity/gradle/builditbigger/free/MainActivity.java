package com.udacity.gradle.builditbigger.free;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.example.android.androidlibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.TaskCompleteListener;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements TaskCompleteListener {

  private InterstitialAd interstitialAd;
  private ProgressBar spinner;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    spinner = findViewById(R.id.loading_bar);
    MobileAds.initialize(this, getString(R.string.ad_mob_app_id));
    interstitialAd = new InterstitialAd(this);
    interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
    interstitialAd.setAdListener(new AdListener() {
      @Override
      public void onAdClosed() {
        getAndShowJoke();
        loadInterstitialAd();
      }
    });
    loadInterstitialAd();

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

  public void tellJoke(View view) {
    if (interstitialAd.isLoaded()) {
      interstitialAd.show();
    } else {
      getAndShowJoke();
    }


  }

  private void getAndShowJoke() {
    spinner.setVisibility(View.VISIBLE);
    new EndpointsAsyncTask(this).execute(this);

  }

  @Override
  public void onTaskComplete(String result) {

    Intent intent = new Intent(MainActivity.this, JokeActivity.class);
    intent.putExtra("joke", result);
    startActivity(intent);
    spinner.setVisibility(View.GONE);

  }

  private void loadInterstitialAd() {
    AdRequest interstitialAdRequest = new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice("C09E051179EC23266E060DB3F44325E6")
        .build();
    interstitialAd.loadAd(interstitialAdRequest);
  }


}
