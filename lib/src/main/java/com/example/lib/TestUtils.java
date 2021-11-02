package com.example.lib;

import com.example.lib.thread.ThreadUtils;

/**
 * author:  yanchunlan
 * date:  2021/11/02 15:15
 * desc:
 */
public class TestUtils {

  public static void main(String[] args) {
    // 获取所有java线程
    Object obj = ThreadUtils.getField(ThreadGroup.class,null, "systemThreadGroup");
    ThreadUtils.getThread(obj);
    Object obj2 = ThreadUtils.getField(ThreadGroup.class,obj, "groups");
    ThreadUtils.getThread(obj2);

    // 获取所有线程(java、native)
    ThreadUtils.dumpThreadNames(ThreadUtils.THREAD_ROOT_DIR);
  }
}
