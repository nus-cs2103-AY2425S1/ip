package eli.task;

import eli.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

  @Test
  public void testAdd() {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    String dateTimeString = "2/1/2221 1111";
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

    Deadline deadlineTask = new Deadline("draw something", dateTime);
    String result = deadlineTask.toFileFormat();
    assertEquals("D | 0 | draw something | 2/1/2221 1111", result);
  }
  @Test
  public void testAdd2(){

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    String dateTimeString = "2/1/1234 1234";
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

    Deadline deadlineTask = new Deadline("Lab assignment", dateTime);
    String result = deadlineTask.toFileFormat();
    assertEquals("D | 0 | Lab assignment | 2/1/1234 1234", result);
  }
}