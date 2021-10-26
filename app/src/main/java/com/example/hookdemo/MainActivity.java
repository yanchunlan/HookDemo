package com.example.hookdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bhook.BhookActivity;
import com.example.epic.EpicActivity;
import com.example.xhook.XhookActivity;

public class MainActivity extends AppCompatActivity {

  static {
    System.loadLibrary("native-lib");
  }

  public native String stringFromJNI();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView tv = findViewById(R.id.sample_text);
    tv.setText(stringFromJNI());
  }

  public void clickToXhook(View view) {
    startActivity(new Intent(this, XhookActivity.class));
  }

  public void clickToBhook(View view) {
    startActivity(new Intent(this, BhookActivity.class));
  }

  public void clickToEpic(View view) {
    startActivity(new Intent(this, EpicActivity.class));
  }
}