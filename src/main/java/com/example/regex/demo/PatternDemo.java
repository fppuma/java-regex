package com.example.regex.demo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.regex.util.GroupUtils;

public class PatternDemo {
  private static final Logger logger = LoggerFactory.getLogger(PatternDemo.class);

  public static void main(String[] args) {

    // compile(regular expression)
    // one single digit
    // furthermore, this is the unique group.
    Pattern p = Pattern.compile("\\d");

    // matcher(input string)
    Matcher m = p.matcher("There are 3 oranges and 2 apples in the table.");

    List<String> items = GroupUtils.getItemsByWholeGroup(m);
    logger.info("items: {}", items); // items: [3, 2]

  }

}
