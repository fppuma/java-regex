package com.example.regex.demo;

import java.util.List;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.regex.util.GroupUtils;
import com.example.regex.util.MatcherUtils;

public class SeveralOcurrencesDemo {
  private static final Logger logger = LoggerFactory.getLogger(SeveralOcurrencesDemo.class);

  public static void main(String[] args) {

    // slash followed by alfanumeric chars
    String regex = "\\/[a-z0-9]*";
    String input = "https://jsonplaceholder.typicode.com/posts/1/comments";

    Matcher m = MatcherUtils.getMatcher(regex, input);
    
    List<String> items = GroupUtils.getItemsByWholeGroup(m);
    logger.info("items: {}", items);//items: [/, /jsonplaceholder, /posts, /1, /comments]

  }

}
