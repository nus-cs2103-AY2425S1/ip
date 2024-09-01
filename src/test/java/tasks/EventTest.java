package tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


public class EventTest {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MM uuuu");

    @Test
    public void createValidTask() {
        LocalDate start = LocalDate.parse("01 01 2001", inputFormatter);
        LocalDate end = LocalDate.parse("02 02 2002", inputFormatter);
        Task task = new Event("Do homework", start, end);
        assertEquals("[ ] Do homework | Event from 01 Jan 2001 to 02 Feb 2002", task.toString());
    }

    @Test
    public void markValidTask() {
        LocalDate start = LocalDate.parse("01 01 2001", inputFormatter);
        LocalDate end = LocalDate.parse("02 02 2002", inputFormatter);
        Task task = new Event("Do homework", start, end);
        task.setDone();
        assertEquals("[x] Do homework | Event from 01 Jan 2001 to 02 Feb 2002", task.toString());
    }

    @Test
    public void getNameTask() {
        LocalDate start = LocalDate.parse("01 01 2001", inputFormatter);
        LocalDate end = LocalDate.parse("02 02 2002", inputFormatter);
        Task task = new Event("Do homework", start, end);
        assertEquals("Do homework", task.getName());
    }

    @Test
    public void isDoneTask() {
        LocalDate start = LocalDate.parse("01 01 2001", inputFormatter);
        LocalDate end = LocalDate.parse("02 02 2002", inputFormatter);
        Task task = new Event("Do homework", start, end);
        assertFalse(task.isDone());
        task.setDone();
        assertTrue(task.isDone());
        task.setUndone();
        assertFalse(task.isDone());
    }
}