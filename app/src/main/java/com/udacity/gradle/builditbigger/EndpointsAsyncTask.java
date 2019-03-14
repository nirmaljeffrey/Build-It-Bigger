package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

  private static final String TAG = "EndpointsAsyncTask";
  private static MyApi myApiService = null;
  private Context context;
  private TaskCompleteListener mTaskCompleteListener;

  public EndpointsAsyncTask(TaskCompleteListener listener) {
    mTaskCompleteListener = listener;
  }

  @Override
  protected String doInBackground(Context... params) {
    if (myApiService == null) {  // Only do this once
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          // options for running against local devappserver
          // - 192.168.43.96 is localhost's IP address in my laptop
          // - turn off compression when running against local devappserver
          .setRootUrl("http://192.168.43.96:8080/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                throws IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
      // end options for devappserver

      myApiService = builder.build();
    }

    context = params[0];

    try {

      return myApiService.getRandomJoke().execute().getData();
    } catch (IOException e) {
      Log.e(TAG, e.getMessage());
      return null;
    }
  }

  @Override
  protected void onPostExecute(String result) {
    if (result != null) {
      mTaskCompleteListener.onTaskComplete(result);
    }
  }

  public interface TaskCompleteListener {

    void onTaskComplete(String result);
  }
}
