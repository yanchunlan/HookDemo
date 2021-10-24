package com.example.xhook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class XhookActivity extends AppCompatActivity {
  private static final String TAG = "xhookActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_xhook);

    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.d(TAG, Thread.currentThread().getName()+" Thread log");
      }
    }).start();
  }

  public void clickHookLog(View view) {
    Log.d(TAG, Thread.currentThread().getName()+" Thread log");

    NativeHandler.getInstance().hookLog();
    Log.d(TAG, Thread.currentThread().getName()+" Thread log");

    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.d(TAG, Thread.currentThread().getName()+" Thread log");
      }
    }).start();
  }
}