package task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventMarking() {
        Event event = new Event("Team meeting", LocalDate.parse("2023-10-10"), LocalDate.parse("2023-10-11"));
        event.mark();
        assertTrue(event.isDone());
    }

    /**
     * Tests unmarking an Event task as not done.
     */
    @Test
    public void testEventUnmarking() {
        Event event = new Event("Team meeting", LocalDate.parse("2023-10-10"), LocalDate.parse("2023-10-11"));
        event.mark();
        event.unmark();
        assertFalse(event.isDone());
    }
}
