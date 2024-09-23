package mysutong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 14, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 10, 16, 0);
        Event event = new Event("Team Meeting", from, to);
        String expected = "[E][ ] Team Meeting (from: Sep 10 2023 2:00pm to: Sep 10 2023 4:00pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testMarkAsDone() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 14, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 10, 16, 0);
        Event event = new Event("Project Presentation", from, to);
        assertFalse(event.isDone());
        event.markAsDone();
        assertTrue(event.isDone());
        String expected = "[E][X] Project Presentation (from: Sep 10 2023 2:00pm to: Sep 10 2023 4:00pm)";
        assertEquals(expected, event.toString());
    }
}
