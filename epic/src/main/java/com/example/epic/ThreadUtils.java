package com.example.epic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.util.Log;

/**
 * author:  yanchunlan
 * date:  2021/11/07 13:20
 * desc:
 */
public class ThreadUtils {
  static final String TAG = "ThreadUtils";

  public static void createThread() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG, "Thread run");
      }
    }).start();

    new MyThread().start();

    final ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 4; i++) {
      // final int num = i;
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          Log.i(TAG, "ExecutorService run");
        }
      });
    }

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
        60L, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>());
    threadPoolExecutor.execute(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG, "ThreadPoolExecutor run");
      }
    });
  }
  static class MyThread extends Thread {
    @Override
    public void run() {
      Log.i(TAG, "MyThread run");
    }
  }
}
