package com.example.dexposed;

import android.app.Application;

import com.taobao.android.dexposed.DexposedBridge;

/**
 * author:  yanchunlan
 * date:  2021/10/24 22:54
 * desc:
 */
public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    // Check whether current device is supported (also initialize Dexposed framework if not yet)
    if (DexposedBridge.canDexposed(this)) {
      // Use Dexposed to kick off AOP stuffs.
    }
  }
}
