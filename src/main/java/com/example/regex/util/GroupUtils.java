package com.example.regex.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GroupUtils {

  public static List<String> getItemsFromUniqueGroup(Matcher m) {
    List<String> list = new ArrayList<String>();
    while (m.find()) {
      list.add(m.group());// without parameter
    }

    return list;
  }

  public static List<String> getItems(Matcher m) {
    List<String> list = new ArrayList<String>();
    while (m.find()) {
      for (int i = 0; i < m.groupCount() + 1; i++) {
        list.add(m.group(i));
      }
    }

    return list;
  }
}
