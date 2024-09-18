package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Event class.
 * This class tests the functionality of parsing date and time for events.
 */
public class EventTest {

    /**
     * Tests if the parseDateTime method correctly parses a full date and time.
     * It checks that the expected LocalDateTime matches the actual parsed LocalDateTime.
     */
    @Test
    public void testParseDateTimeWithFullDateTime() {
        Event event = new Event();
        String input = "10/9/2024 3pm";
        LocalDateTime expected = LocalDateTime.parse("2024-09-10 1500", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime actual = event.parseDateTime(input);
        assertEquals(expected, actual);
    }

    /**
     * Tests if the parseDateTime method correctly parses a date without a time.
     * It checks that the expected LocalDateTime (with time set to 00:00) matches the actual parsed LocalDateTime.
     */
    @Test
    public void testParseDateTimeWithOnlyDate() {
        Event event = new Event();
        String input = "10/9/2024";
        LocalDateTime expected = LocalDateTime.parse("2024-09-10T00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime actual = event.parseDateTime(input);
        assertEquals(expected, actual);
    }
}
