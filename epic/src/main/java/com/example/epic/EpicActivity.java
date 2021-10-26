package com.example.epic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;

public class EpicActivity extends AppCompatActivity {
  private static final String TAG = "EpicActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_epic);
  }

  public void clickNewThread(View view) {
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

  }

  static class MyThread extends Thread {
    @Override
    public void run() {
      Log.i(TAG, "MyThread run");
    }
  }

  public void clickHookThread(View view) {

    try {

      class ThreadMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
          super.beforeHookedMethod(param);
          Thread t = (Thread) param.thisObject;
          t.setName(this.getClass().getSimpleName()+" "+t.getName());
          Log.i(TAG, "thread:" + t + ", started..");
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
          super.afterHookedMethod(param);
          Thread t = (Thread) param.thisObject;
          t.setName(this.getClass().getSimpleName()+" "+t.getName());
          Log.i(TAG, "thread:" + t + ", exit..");
        }
      }

      DexposedBridge.hookAllConstructors(Thread.class, new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
          super.afterHookedMethod(param);
          Thread thread = (Thread) param.thisObject;
          Class<?> clazz = thread.getClass();
          if (clazz != Thread.class) {
            Log.d(TAG, "found class extend Thread:" + clazz);
            DexposedBridge.findAndHookMethod(clazz, "run", new ThreadMethodHook());
          }
          Log.d(TAG, "Thread: " + thread.getName() + " class:" + thread.getClass() +  " is created.");
        }
      });
      DexposedBridge.findAndHookMethod(Thread.class, "run", new ThreadMethodHook());

    } catch (Throwable e) {
      Log.e(TAG, "hook failed", e);
    }
  }
}