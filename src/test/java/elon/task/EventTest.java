package elon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventCreation() {
        LocalDateTime start = LocalDateTime.of(2024, 9, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 9, 20, 12, 0);
        Event event = new Event("Meeting", false, start, end);
        String expectedDescription = "[E][ ] Meeting (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + ")";
        assertEquals(expectedDescription, event.toString());
    }

    @Test
    public void testSnooze() {
        LocalDateTime start = LocalDateTime.of(2024, 9, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 9, 20, 12, 0);
        Event event = new Event("Meeting", false, start, end);
        LocalDateTime newEnd = LocalDateTime.of(2024, 9, 21, 14, 0);
        event.snooze(newEnd);
        assertEquals(newEnd, event.end);
    }
}

