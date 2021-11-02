package com.example.lib.thread;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;

/**
 * author:  yanchunlan
 * date:  2021/10/27 18:54
 * desc:
 */
public class ThreadUtils {
  public static final String TAG = "ThreadUtils";

  public static final File THREAD_ROOT_DIR = new File("/proc/self/task");


  public static Object getField(Class clazz, Object obj, String name) {
    try {
      Field field = clazz.getDeclaredField(name);
      field.setAccessible(true);
      return field.get(obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void getThread(Object obj) {
    if (obj == null || !(obj instanceof ThreadGroup)) {
      return;
    }
    ThreadGroup group = ((ThreadGroup) obj);
    int activeCount = (int) (group.activeCount() * 1.5f);
    Thread[] list = new Thread[activeCount];
    int threadCount = group.enumerate(list);
    Log.d(TAG, "threadName group " + group.getName());
    for (int i = 0; i < threadCount; i++) {
      Thread threads = list[i];
      Log.d(TAG, "threadName thread " + threads.getName());
      if (threads.getState() == Thread.State.BLOCKED ||
          threads.getState() == Thread.State.WAITING ||
          threads.getState() == Thread.State.TIMED_WAITING) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = threads.getStackTrace();
        for (StackTraceElement e : stackTrace) {
          sb.append(e != null ? e.toString() : "null").append("\n");
        }
//        Log.d(TAG, "threadName getStackTrace " + sb.toString());
      }
    }
  }

  @NonNull
  public static ThreadRecord dumpThreadNames(File threadRootDir) {
    if (threadRootDir == null || !threadRootDir.exists()) {
      return null;
    }
    File[] threads = threadRootDir.listFiles();
    if (threads == null) {
      return null;
    }

    List<String> list = new ArrayList<>();

    for (File threadDir : threads) {
      File threadNameFile = new File(threadDir, "comm");
      String name;
      try {
        name = IOUtils.read2String(threadNameFile);
        if (!TextUtils.isEmpty(name)) {
          list.add(name.replace("\n", ""));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (list.size() <= 0) {
      return null;
    }

    ThreadRecord threadRecord = new ThreadRecord();
    threadRecord.mThreadCount = list.size();
    threadRecord.mThreadList = list;
    threadRecord.mThreadFilterJson = filterThread(list);
    Log.d(TAG, "dumpThreadNames " + threadRecord.toString());
    return threadRecord;
  }

  /**
   * 过滤线程
   *
   * @param threadLists 线程数
   * @return 线程个数大于1 && 线程个数从高到低优先级，取前10
   */
  @NonNull
  public static HashMap<String, Integer> filterThread(@NonNull List<String> threadLists) {
    if (threadLists == null || threadLists.isEmpty()) {
      return null;
    }
    Map<String, Integer> tempMaps = new HashMap<>();

    // list to map
    for (String name : threadLists) {
      if (name.startsWith("pool-") && name.endsWith("-thread-")) {
        Integer v = tempMaps.get("pool-num-thread-");
        if (v == null) {
          tempMaps.put("pool-num-thread-", 1);
        } else {
          tempMaps.put("pool-num-thread-", v + 1);
        }
      } else if (name.startsWith("Thread-")) {
        Integer v = tempMaps.get("Thread-num");
        if (v == null) {
          tempMaps.put("Thread-num", 1);
        } else {
          tempMaps.put("Thread-num", v + 1);
        }
      } else if (name.startsWith("Binder:")) {
        Integer v = tempMaps.get("Binder:num");
        if (v == null) {
          tempMaps.put("Binder:num", 1);
        } else {
          tempMaps.put("Binder:num", v + 1);
        }
      } else {
        Integer value = tempMaps.get(name);
        if (value == null) {
          tempMaps.put(name, 1);
        } else {
          tempMaps.put(name, value + 1);
        }
      }
    }

    // map remove value=1
    Iterator<Map.Entry<String, Integer>> iterator = tempMaps.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, Integer> entry = iterator.next();
      if (entry.getValue() == 1) {
        iterator.remove();
      }
    }

    // sort map
    List<Map.Entry<String, Integer>> tempLists = new ArrayList<>(tempMaps.entrySet());
    Collections.sort(tempLists, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    });

    // subList 10
    if (tempLists.size() > 10) {
      tempLists = tempLists.subList(0, 10);
    }

    HashMap<String, Integer> threadMaps = new HashMap<>();
    for (Map.Entry<String, Integer> entry : tempLists) {
      threadMaps.put(entry.getKey(), entry.getValue());
    }
    return threadMaps;
  }

  public static class ThreadRecord {
    public int mThreadCount;
    public List<String> mThreadList;
    public HashMap<String, Integer> mThreadFilterJson;
  }
}
