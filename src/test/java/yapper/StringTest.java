package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class StringTest {
    @Test
    public void toDoToStringTest() {
        ToDo todo = new ToDo("buy bread");
        todo.setIsDone(true);
        String expected = "[T][X] buy bread";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toDoToFileTest() {
        ToDo todo = new ToDo("buy bread");
        todo.setIsDone(true);
        String expected = "T D--buy bread";
        String actual = todo.toFile();
        assertEquals(expected, actual);
    }

    @Test
    public void deadlineToStringTest() {
        Deadline deadline = new Deadline("buy bread", LocalDateTime.of(2024, 12, 31, 18, 0));
        deadline.setIsDone(true);
        String expected = "[D][X] buy bread (BY: Dec 31 2024 18:00)";
        String actual = deadline.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deadlineToFileTest() {
        Deadline deadline = new Deadline("buy bread", LocalDateTime.of(2024, 12, 31, 18, 0));
        deadline.setIsDone(true);
        String expected = "D D--buy bread--2024-12-31T18:00";
        String actual = deadline.toFile();
        assertEquals(expected, actual);
    }
}
