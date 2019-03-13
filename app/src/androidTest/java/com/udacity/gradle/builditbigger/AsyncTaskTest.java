package com.udacity.gradle.builditbigger;


import static org.junit.Assert.assertNotNull;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.TaskCompleteListener;
import java.util.concurrent.ExecutionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
      MainActivity.class);

  @Test
  public void asyncTaskTest() throws ExecutionException, InterruptedException {
    EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new TaskCompleteListener() {
      @Override
      public void onTaskComplete(String result) {

      }
    });
    endpointsAsyncTask.execute(mainActivityActivityTestRule.getActivity());
    String joke = endpointsAsyncTask.get();
    assertNotNull(joke);
  }
}
