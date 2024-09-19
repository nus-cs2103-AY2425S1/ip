package krona.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEventCreation() {
        Event event = new Event("Team Meeting", "15/9/2024 1400", "15/9/2024 1600");
        assertEquals("Team Meeting", event.getDescription());
        assertEquals("15/9/2024 1400", event.getStartDateTime());
        assertEquals("15/9/2024 1600", event.getEndDateTime());
    }
    @Test
    public void testEventToString() {
        Event event = new Event("Team Meeting", "15/9/2024 1400", "15/9/2024 1600");
        assertEquals("[E][ ] Team Meeting (from: Sep 15 2024, 2:00 pm to: Sep 15 2024, 4:00 pm)", event.toString());

        event.markDone();
        assertEquals("[E][X] Team Meeting (from: Sep 15 2024, 2:00 pm to: Sep 15 2024, 4:00 pm)", event.toString());
    }

    @Test
    public void testInvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("Invalid Event", "2024-09-15 14:00", "2024-09-15 16:00");
        });
    }
}
