package friendlybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * A JUnit test for Event.
 */
public class EventTest {
    /**
     * Tests String representation of a complete Event Task.
     */
    @Test
    public void createEvent_markAsCompleted_correctStringOutput() {
        LocalDate dummyDate = LocalDate.now();
        String formattedDate = dummyDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Event task = new Event("test task", dummyDate, dummyDate);
        task.markAsDone();
        assertEquals("[E][X] test task (from: " + formattedDate + " to: " + formattedDate + ")",
                task.toString());
    }

    /**
     * Tests String representation of an incomplete Event Task.
     */
    @Test
    public void createEvent_incomplete_correctStringOutput() {
        LocalDate dummyDate = LocalDate.now();
        String formattedDate = dummyDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Event task = new Event("test task", dummyDate, dummyDate);
        assertEquals("[E][ ] test task (from: " + formattedDate + " to: " + formattedDate + ")",
                task.toString());
    }
}
