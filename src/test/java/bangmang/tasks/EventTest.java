package bangmang.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {

    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.of(2024, 9, 17, 10, 0);
        LocalDateTime to = LocalDateTime.of(2024, 9, 18, 12, 0);
        Event event = new Event("Test Event", from, to);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM HH:mm");
        String expected = "[E][ ] Test Event | " + from.format(formatter) + " - " + to.format(formatter);
        assertEquals(expected, event.toString());
        event.markTask();
        assertTrue(event.getIsDone());
    }
}
