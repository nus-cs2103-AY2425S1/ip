package skd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import task.Event;


public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event("project meeting", "2024-08-30 14:00", "2024-08-30 16:00");
        assertEquals("[E][ ] project meeting (from: Aug 30 2024, 14:00 to: Aug 30 2024, 16:00)", event.toString());
    }

    @Test
    public void testMarkAsDone() {
        Event event = new Event("project meeting", "2024-08-30 14:00", "2024-08-30 16:00");
        event.markAsDone();
        assertTrue(event.isDone());
        assertEquals("[E][X] project meeting (from: Aug 30 2024, 14:00 to: Aug 30 2024, 16:00)", event.toString());
    }

    @Test
    public void testEventWithIsDone() {
        Event event = new Event("project meeting", "2024-08-30 14:00", "2024-08-30 16:00", true);
        assertTrue(event.isDone());
        assertEquals("[E][X] project meeting (from: Aug 30 2024, 14:00 to: Aug 30 2024, 16:00)", event.toString());
    }
}
