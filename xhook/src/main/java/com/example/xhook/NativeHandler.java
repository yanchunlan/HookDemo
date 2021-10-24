package com.example.xhook;

/**
 * author:  yanchunlan
 * date:  2021/10/24 16:43
 * desc:
 */
public class NativeHandler {
  static {
    System.loadLibrary("xhook-lib");
  }
  private static final NativeHandler instance = new NativeHandler();

  public static NativeHandler getInstance() {
    return instance;
  }

  private NativeHandler() {
  }

  public native void hookLog();

  public native int refresh(boolean async);

  public native void clear();

  public native void enableDebug(boolean flag);

  public native void enableSigSegvProtection(boolean flag);
}
