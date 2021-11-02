package com.example.lib.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

/**
 * author:  yanchunlan
 * date:  2021/11/02 15:18
 * desc:
 */
public class IOUtils {
  public static String read2String(File file) throws IOException {
    Reader reader = new InputStreamReader(
        new BufferedInputStream(
            new FileInputStream(file)), "UTF-8");
    return read2String(reader);
  }

  public static String read2String(Reader reader) throws IOException {
    try (StringWriter writer = new StringWriter()) {
      char[] buffer = new char[1024];
      int count;
      while ((count = reader.read(buffer)) != -1) {
        writer.write(buffer, 0, count);
      }
      return writer.toString();
    } finally {
      reader.close();
    }
  }
}
