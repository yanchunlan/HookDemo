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
    ThreadUtils.ThreadRecord startThreadRecord = ThreadUtils.dumpThreadNames(ThreadUtils.THREAD_ROOT_DIR);
    ThreadUtils.ThreadRecord endThreadRecord = ThreadUtils.dumpThreadNames(ThreadUtils.THREAD_ROOT_DIR);

    // 增量线程 = 结束线程-开始线程
    for (String threadName : startThreadRecord.mThreadList) {
      endThreadRecord.mThreadList.remove(threadName);
    }
    endThreadRecord.mThreadCount = endThreadRecord.mThreadList.size();
    endThreadRecord.mThreadFilterJson = ThreadUtils.filterThread(endThreadRecord.mThreadList);
  }
}
