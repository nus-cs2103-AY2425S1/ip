package bobby.ui;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


/**
 * Tests the functionality of the {@link Deadlines} class.
 * This class ensures that the methods in the Deadlines class
 * work correctly for task initialization, marking as done,
 * unmarking, and formatting for display and storage.
 */
public class DeadlinesTest {

    // Initialize a LocalDateTime object for testing
    private LocalDateTime deadlineDateTime = LocalDateTime.of(2024, 9, 15, 14, 30);
    // Initialize a new Deadlines object before each test
    private Deadlines deadline = new Deadlines("Submit assignment", deadlineDateTime);


    @Test
    public void testDeadlineInitialization() {
        // Test that the Deadlines object is initialized correctly
        assertEquals("Submit assignment", deadline.description);
        assertEquals(deadline.getStatus(), "0");
        assertEquals(deadlineDateTime, deadline.by);
    }

    @Test
    public void testMarkAsDone() {
        // Test marking the task as done
        deadline.markAsDone();
        assertEquals(deadline.getStatus(), "1");
    }

    @Test
    public void testUnMark() {
        // Test unmarking the task (setting it back to not done)
        deadline.unMark();
        assertEquals(deadline.getStatus(), "0");
    }

    @Test
    public void testToString() {
        // Test the string representation of the Deadlines object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String expected = "[D][ ] Submit assignment (by: " + deadlineDateTime.format(formatter) + ")";
        assertEquals(expected, deadline.toString());

        deadline.markAsDone();
        expected = "[D][X] Submit assignment (by: " + deadlineDateTime.format(formatter) + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToStore() {
        // Test the toStore method which formats the task for storage
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String expected = "D/0/Submit assignment/" + deadlineDateTime.format(formatter);
        assertEquals(expected, deadline.toStore());

        deadline.markAsDone();
        expected = "D/1/Submit assignment/" + deadlineDateTime.format(formatter);
        assertEquals(expected, deadline.toStore());
    }
}

