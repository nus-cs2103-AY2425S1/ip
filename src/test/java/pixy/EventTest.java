package pixy;

import org.junit.jupiter.api.Test;
import pixy.tasks.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testValidDateFormat() {
        Event event = new Event("Meeting", "31/08/2024 0900", "31/08/2024 1100");
        assertEquals("Aug 31 2024, 9:00 am", event.getFrom());
        assertEquals("Aug 31 2024, 11:00 am", event.getTo());
    }

    @Test
    public void testAlternativeDateFormat() {
        Event event = new Event("Conference", "Aug 31 2024, 2:00 pm", "Aug 31 2024, 4:00 pm");
        assertEquals("Aug 31 2024, 2:00 pm", event.getFrom());
        assertEquals("Aug 31 2024, 4:00 pm", event.getTo());
    }

    @Test
    public void testInvalidDateFormat() {
        try {
            new Event("Invalid Event", "invalid date", "invalid date");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid date format: invalid date or invalid date", e.getMessage());
        }
    }
}
