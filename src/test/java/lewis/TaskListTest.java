package lewis;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for the TaskList class in Lewis
 */
public class TaskListTest {
    @Test
    public void testToString_withValidDeadline() {
        // Arrange
        LocalDate date = LocalDate.parse("2024-09-20");
        LocalTime time = LocalTime.parse("23:59");
        LocalDateTime by = LocalDateTime.of(date,time);
        LocalDate date2 = LocalDate.parse("2024-09-21");
        LocalTime time2 = LocalTime.parse("12:00");
        LocalDateTime from = LocalDateTime.of(date2,time2);
        LocalDate date3 = LocalDate.parse("2024-09-21");
        LocalTime time3 = LocalTime.parse("13:30");
        LocalDateTime to = LocalDateTime.of(date3,time3);


        Deadline deadline = Deadline.of("Submit assignment", "NOT_DONE",by);
        Event event = Event.of("Dinner","NOT_DONE",from ,to);
        Todo todo = new Todo("Clean up iP");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add(deadline.toCsv());
        tasks.add(event.toCsv());
        tasks.add(todo.toCsv());

        TaskList taskList = TaskList.of(tasks);
        // Act
        String actual = taskList.toString();

        // Assert
        String expected = """
                1. [D][ ] Submit assignment (Deadline: Sept 20 2024 23:59)
                2. [E][ ] Dinner (From: Sept 21 2024, 12:00 To: Sept 21 2024, 13:30)
                3. [T][ ] Clean up iP
                """;
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkedToString_withValidDeadline() {
        // Arrange
        LocalDate date = LocalDate.parse("2024-09-20");
        LocalTime time = LocalTime.parse("23:59");
        LocalDateTime by = LocalDateTime.of(date,time);
        LocalDate date2 = LocalDate.parse("2024-09-21");
        LocalTime time2 = LocalTime.parse("12:00");
        LocalDateTime from = LocalDateTime.of(date2,time2);
        LocalDate date3 = LocalDate.parse("2024-09-21");
        LocalTime time3 = LocalTime.parse("13:30");
        LocalDateTime to = LocalDateTime.of(date3,time3);


        Deadline deadline = Deadline.of("Submit assignment", "DONE",by);
        Event event = Event.of("Dinner","NOT_DONE",from ,to);
        Todo todo = new Todo("Clean up iP", "DONE");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add(deadline.toCsv());
        tasks.add(event.toCsv());
        tasks.add(todo.toCsv());

        TaskList taskList = TaskList.of(tasks);
        // Act
        String actual = taskList.toString();

        // Assert
        String expected = """
                1. [D][X] Submit assignment (Deadline: Sept 20 2024 23:59)
                2. [E][ ] Dinner (From: Sept 21 2024, 12:00 To: Sept 21 2024, 13:30)
                3. [T][X] Clean up iP
                """;
        assertEquals(expected, actual);
    }

    @Test
    public void testToSaveFile_withValidDeadline() {
        // Arrange
        LocalDate date = LocalDate.parse("2024-09-20");
        LocalTime time = LocalTime.parse("23:59");
        LocalDateTime by = LocalDateTime.of(date,time);
        LocalDate date2 = LocalDate.parse("2024-09-21");
        LocalTime time2 = LocalTime.parse("12:00");
        LocalDateTime from = LocalDateTime.of(date2,time2);
        LocalDate date3 = LocalDate.parse("2024-09-21");
        LocalTime time3 = LocalTime.parse("13:30");
        LocalDateTime to = LocalDateTime.of(date3,time3);


        Deadline deadline = Deadline.of("Submit assignment", "DONE",by);
        Event event = Event.of("Dinner","NOT_DONE",from ,to);
        Todo todo = new Todo("Clean up iP", "DONE");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add(deadline.toCsv());
        tasks.add(event.toCsv());
        tasks.add(todo.toCsv());

        TaskList taskList = TaskList.of(tasks);

        ArrayList<String> saveFile = TaskList.toSaveFile();


        // Act
        String actual = saveFile.toString();

        // Assert
        String expected = "[Deadline,Submit assignment,DONE,2024-09-20T23:59," +
                " Event,Dinner,NOT_DONE,2024-09-21T12:00,2024-09-21T13:30," +
                " Todo,Clean up iP,DONE]";
        assertEquals(expected, actual);
    }
}
