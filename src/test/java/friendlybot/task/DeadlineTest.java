package friendlybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * A JUnit test for Deadline.
 */
public class DeadlineTest {
    /**
     * Tests String representation of a completed Deadline Task.
     */
    @Test
    public void createDeadline_markAsCompleted_correctStringOutput() {
        LocalDate dummyDate = LocalDate.now();
        String formattedDate = dummyDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Deadline task = new Deadline("test task", dummyDate);
        task.markAsDone();
        assertEquals("[D][X] test task (by: " + formattedDate + ")", task.toString());
    }

    /**
     * Tests String representation of an incomplete Deadline Task.
     */
    @Test
    public void createDeadline_incomplete_correctStringOutput() {
        LocalDate dummyDate = LocalDate.now();
        String formattedDate = dummyDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        Deadline task = new Deadline("test task", dummyDate);
        assertEquals("[D][ ] test task (by: " + formattedDate + ")", task.toString());
    }
}
