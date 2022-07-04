package com.example.regex.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
  
  @Test
  @DisplayName("Protocol and Domain")
  public void testProtocolAndDomain02() {
    
    String regex = "(https?://)([^:^/]*)";
    Pattern p = Pattern.compile(regex);

    String github = "http://localhost:8080/marketplace";
    Matcher m = p.matcher(github);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("localhost", 
      () -> assertEquals(3, items.size()),
      () -> assertEquals("http://localhost", items.get(0)),
      () -> assertEquals("http://", items.get(1)), //protocol
      () -> assertEquals("localhost", items.get(2))//domain
    );
  }
  
  @Test
  @DisplayName("Protocol, Domain and Port")
  public void testProtocolDomainAndPort() {

    //(:\\d*)? :digits (0 or 1)
    String regex = "(https?://)([^:^/]*)(:\\d*)?";
    Pattern p = Pattern.compile(regex);

    String localhost = "http://localhost:8080/marketplace";
    Matcher m = p.matcher(localhost);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("localhost", 
      () -> assertEquals(4, items.size()),
      () -> assertEquals("http://localhost:8080", items.get(0)),
      () -> assertEquals("http://", items.get(1)),  //protocol
      () -> assertEquals("localhost", items.get(2)),//domain
      () -> assertEquals(":8080", items.get(3))      //port
    );
  }
  
  @Test
  @DisplayName("Protocol, Domain and Port")
  public void testProtocolDomainAndPort02() {

    //(:\\d*)? :digits (0 or 1)
    String regex = "(https?://)([^:^/]*)(:\\d*)?";
    Pattern p = Pattern.compile(regex);

    String localhost = "https://github.com/marketplace";
    Matcher m = p.matcher(localhost);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("localhost", 
      () -> assertEquals(4, items.size()),
      () -> assertEquals("https://github.com", items.get(0)),
      () -> assertEquals("https://", items.get(1)),  //protocol
      () -> assertEquals("github.com", items.get(2)),//domain
      () -> assertNull(items.get(3))                 //without port
    );
  }
  
  @Test
  @DisplayName("Protocol, Domain, Port and Uri")
  public void testProtocolDomainPortAndUri() {

    //(.*)? any characters
    String regex = "(https?://)([^:^/]*)(:\\d*)?(.*)?";
    Pattern p = Pattern.compile(regex);

    String localhost = "https://github.com/marketplace";
    Matcher m = p.matcher(localhost);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("localhost", 
      () -> assertEquals(5, items.size()),
      () -> assertEquals("https://github.com/marketplace", items.get(0)),
      () -> assertEquals("https://", items.get(1)),    //protocol
      () -> assertEquals("github.com", items.get(2)),  //domain
      () -> assertNull(items.get(3)),                  //without port
      () -> assertEquals("/marketplace", items.get(4))//uri
    );
  }
  
  @Test
  @DisplayName("Localhost: Protocol, Domain, Port and Uri")
  public void testProtocolDomainPortAndUri02() {

    //(.*)? any characters
    String regex = "(https?://)([^:^/]*)(:\\d*)?(.*)?";
    Pattern p = Pattern.compile(regex);

    String localhost = "http://localhost:8080/marketplace";
    Matcher m = p.matcher(localhost);
    List<String> items = GroupUtils.getItemsByGroups(m);
    logger.info("items: {}", items);
    assertAll("localhost", 
      () -> assertEquals(5, items.size()),
      () -> assertEquals("http://localhost:8080/marketplace", items.get(0)),
      () -> assertEquals("http://", items.get(1)),    //protocol
      () -> assertEquals("localhost", items.get(2)),  //domain
      () -> assertEquals(":8080", items.get(3)),      //port
      () -> assertEquals("/marketplace", items.get(4))//uri
    );
  }
}
