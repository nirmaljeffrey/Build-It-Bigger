package com.example.android.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

  private String joke;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_joke);
    if (getIntent() != null) {
      Intent intent = getIntent();
      joke = intent.getStringExtra("joke");
    }
    TextView jokeTextView = findViewById(R.id.joke_text_view);
    jokeTextView.setText(joke);
  }
}
