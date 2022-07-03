package com.example.regex.demo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.regex.util.GroupUtils;

public class SeveralGroupsDemo {
  private static final Logger logger = LoggerFactory.getLogger(SeveralGroupsDemo.class);

  public static void main(String[] args) {
    // compile(regular expression)
    // 1st group: single digit
    // 2nd group: single char
    Pattern p = Pattern.compile("(\\d)([a-z])");

    // matcher(input string)
    String input = "3a + 5b = 25, where a and b are integers";
    
    Matcher m1 = p.matcher(input);
    List<String> items = GroupUtils.getItems(m1);
    logger.info("items: {}", items);// items: [3a, 3, a, 5b, 5, b]
    
    Matcher m2 = p.matcher(input);
    List<String> items2 = GroupUtils.getItemsFromUniqueGroup(m2);
    logger.info("items2: {}", items2);//items2: [3a, 5b]

  }

}
