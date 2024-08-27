package friendlybot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
