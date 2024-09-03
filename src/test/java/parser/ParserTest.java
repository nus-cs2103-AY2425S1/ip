package parser;

import eli.parser.Parser;
import eli.task.Deadline;
import eli.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

  @Test
  public void testAdd() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    String dateTimeString = "2/1/2221 1111";
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

    Task task = Parser.parseTaskFromFile("D | 0 | CS2105 Assignment | 2/1/2221 1111");
    assertEquals(new Deadline("CS2105 Assignment", dateTime), task);
  }
  @Test
  public void testAdd2() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    String dateTimeString = "2/1/1234 1234";
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

    Task task = Parser.parseTaskFromFile("D | 0 | Lab1 | 2/1/1234 1234");
    assertEquals(new Deadline("Lab1", dateTime), task);
  }
}
