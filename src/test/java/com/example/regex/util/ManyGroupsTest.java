package com.example.regex.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for review this example: 
 * https://snippets.aktagon.com/snippets/72-split-a-url-into-protocol-domain-port-and-uri-using-regular-expressions
 * @author fppuma
 *
 */
public class ManyGroupsTest {
  private static final Logger logger = LoggerFactory.getLogger(ManyGroupsTest.class);

  @Test
  @DisplayName("Protocol and Domain")
  public void testProtocolAndDomain() {
    
    //(https?://) http s(0 or 1) followed by ://
    //([^:^/]*) any text ...until : or / (except : or /)
    String regex = "(https?://)([^:^/]*)";
    Pattern p = Pattern.compile(regex);

    String github = "https://github.com/marketplace";
    Matcher m = p.matcher(github);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("github", 
      () -> assertEquals(3, items.size()),
      () -> assertEquals("https://github.com", items.get(0)),
      () -> assertEquals("https://", items.get(1)), //protocol
      () -> assertEquals("github.com", items.get(2))//domain
    );
  }
}
