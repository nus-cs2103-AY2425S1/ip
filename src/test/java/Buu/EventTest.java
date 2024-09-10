package Buu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Event class in the GPT application.
 */
public class EventTest {

    @Test
    public void testConstructor_withValidInput() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 10, 12, 0);
        Event event = new Event("Team meeting", from, to);

        String expectedString = "[E][ ] Team meeting (from: Sep 10 2023, 10:00am to: Sep 10 2023, 12:00pm)";
        assertEquals(expectedString, event.toString());
    }
    @Test
    public void testToString_withValidDate() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 10, 12, 0);
        Event event = new Event("Team meeting", from, to);

        String expectedString = "[E][ ] Team meeting (from: Sep 10 2023, 10:00am to: Sep 10 2023, 12:00pm)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void testToString_withInvalidDate() {
        Event event = new Event("Team meeting", null, null);

        String expectedString = "[E][ ] Team meeting (from: [Invalid Date] to: [Invalid Date])";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void testToFileFormat_withValidDate() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 10, 12, 0);
        Event event = new Event("Team meeting", from, to);

        String expectedFormat = "E | 0 | Team meeting | 2023-09-10 1000 | 2023-09-10 1200";
        assertEquals(expectedFormat, event.toFileFormat());
    }

    @Test
    public void testToFileFormat_withInvalidDate() {
        Event event = new Event("Team meeting", null, null);

        String expectedFormat = "E | 0 | Team meeting | [Invalid Date] | [Invalid Date]";
        assertEquals(expectedFormat, event.toFileFormat());
    }
}
