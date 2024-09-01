package tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


public class DeadlineTest {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MM uuuu");

    @Test
    public void createValidTask() {
        LocalDate date = LocalDate.parse("01 01 2001", inputFormatter);
        Task task = new Deadline("Do homework", date);
        assertEquals("[ ] Do homework | Deadline by 01 Jan 2001", task.toString());
    }

    @Test
    public void markValidTask() {
        LocalDate date = LocalDate.parse("01 01 2001", inputFormatter);
        Task task = new Deadline("Do homework", date);
        task.setDone();
        assertEquals("[x] Do homework | Deadline by 01 Jan 2001", task.toString());
    }

    @Test
    public void getNameTask() {
        LocalDate date = LocalDate.parse("01 01 2001", inputFormatter);
        Task task = new Deadline("Do homework", date);
        assertEquals("Do homework", task.getName());
    }

    @Test
    public void isDoneTask() {
        LocalDate date = LocalDate.parse("01 01 2001", inputFormatter);
        Task task = new Deadline("Do homework", date);
        assertFalse(task.isDone());
        task.setDone();
        assertTrue(task.isDone());
        task.setUndone();
        assertFalse(task.isDone());
    }

}
