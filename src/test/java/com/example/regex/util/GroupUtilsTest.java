package com.example.regex.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupUtilsTest {
  private static final Logger logger = LoggerFactory.getLogger(GroupUtilsTest.class);

  @Test
  @DisplayName("One Digit: \\d")
  public void testOneDigit() {

    String regex = "\\d";
    String input = "Only 2 cups of coffee and 3 of tea, please";
    Matcher m = MatcherUtils.getMatcher(regex, input);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);

    assertEquals(2, items.size());
    assertEquals("2", items.get(0));
    assertEquals("3", items.get(1));
  }

  @Test
  @DisplayName("http or https: https?")
  public void testHttpOrHttps() {

    String regex = "https?";
    Pattern p = Pattern.compile(regex);

    String github = "https://github.com/";
    Matcher m = p.matcher(github);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("github", 
      () -> assertEquals(1, items.size()),
      () -> assertEquals("https", items.get(0))
    );

    String localhost = "http://localhost:8080/";
    Matcher matcher = p.matcher(localhost);
    List<String> list = GroupUtils.getItemsByGroups(matcher);
    logger.info("items: {}", list);
    assertAll("localhost", 
      () -> assertEquals(1, list.size()), 
      () -> assertEquals("http", list.get(0))
    );

  }
}
