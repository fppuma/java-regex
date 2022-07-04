package com.example.regex.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtils {

  public static Matcher getMatcher(String regex, String input) {
    Pattern p = Pattern.compile(regex);
    return p.matcher(input);
  }
}
