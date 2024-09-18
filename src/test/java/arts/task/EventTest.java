package arts.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the test class for the Event class.
 * This class contains unit tests for various methods in the Event class.
 */
public class EventTest {

    private Event event;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Sets up the test environment before each test.
     * Initializes the 'from' and 'to' LocalDateTime objects and creates a new Event instance.
     */
    @BeforeEach
    public void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        from = LocalDateTime.parse("2024-08-29 1000", formatter);
        to = LocalDateTime.parse("2024-08-29 1200", formatter);
        event = new Event("Team meeting", from, to);
    }

    /**
     * Tests the toString() method of the Event class.
     * Verifies that the string representation of the event matches the expected format.
     */
    @Test
    public void testToString() {
        String expected = "[E][ ] Team meeting (from: Aug 29 2024, 10:00 AM to: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, event.toString(), "The string representation of the event is incorrect.");
    }

    /**
     * Tests the toFileFormat() method of the Event class.
     * Verifies that the file format of the event matches the expected format.
     */
    @Test
    public void testToFileFormat() {
        String expected = "E | 0 | Team meeting | 2024-08-29 1000 | 2024-08-29 1200";
        assertEquals(expected, event.toFileFormat(), "The file format of the event is incorrect.");
    }

    /**
     * Tests the markAsDone() method of the Event class.
     * Ensures that the event is marked as done and its string representation is updated accordingly.
     */
    @Test
    public void testMarkAsDone() {
        event.markAsDone();
        String expected = "[E][X] Team meeting (from: Aug 29 2024, 10:00 AM to: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, event.toString(), "The event should be marked as done.");
    }
}
