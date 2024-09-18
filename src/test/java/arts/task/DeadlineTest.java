package arts.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the DeadlineTest class contains unit tests for the Deadline class.
 * It verifies the correct behavior of the Deadline class, including string representation,
 * file format conversion, and marking tasks as done.
 */
public class DeadlineTest {

    private Deadline deadline;
    private LocalDateTime dateTime;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes a Deadline instance with a specified task description and due date.
     */
    @BeforeEach
    public void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        dateTime = LocalDateTime.parse("2024-08-29 1200", formatter);
        deadline = new Deadline("Submit assignment", dateTime);
    }

    /**
     * Tests the string representation of the Deadline instance.
     * Verifies that the toString method returns the correct format.
     */
    @Test
    public void testToString() {
        String expected = "[D][ ] Submit assignment (by: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, deadline.toString(), "The string representation of the deadline is incorrect.");
    }

    /**
     * Tests the file format conversion of the Deadline instance.
     * Verifies that the toFileFormat method returns the correct format for file storage.
     */
    @Test
    public void testToFileFormat() {
        String expected = "D | 0 | Submit assignment | 2024-08-29 1200";
        assertEquals(expected, deadline.toFileFormat(), "The file format of the deadline is incorrect.");
    }

    /**
     * Tests the functionality of marking a Deadline instance as done.
     * Verifies that the task is marked correctly and the string representation is updated.
     */
    @Test
    public void testMarkAsDone() {
        deadline.markAsDone();
        String expected = "[D][X] Submit assignment (by: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, deadline.toString(), "The deadline should be marked as done.");
    }
}
