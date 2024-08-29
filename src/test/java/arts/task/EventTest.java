package arts.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event event;
    private LocalDateTime from;
    private LocalDateTime to;

    @BeforeEach
    public void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        from = LocalDateTime.parse("2024-08-29 1000", formatter);
        to = LocalDateTime.parse("2024-08-29 1200", formatter);
        event = new Event("Team meeting", from, to);
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] Team meeting (from: Aug 29 2024, 10:00 AM to: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, event.toString(), "The string representation of the event is incorrect.");
    }

    @Test
    public void testToFileFormat() {
        String expected = "E | 0 | Team meeting | 2024-08-29 1000 | 2024-08-29 1200";
        assertEquals(expected, event.toFileFormat(), "The file format of the event is incorrect.");
    }

    @Test
    public void testMarkAsDone() {
        event.markAsDone();
        String expected = "[E][X] Team meeting (from: Aug 29 2024, 10:00 AM to: Aug 29 2024, 12:00 PM)";
        assertEquals(expected, event.toString(), "The event should be marked as done.");
    }
}