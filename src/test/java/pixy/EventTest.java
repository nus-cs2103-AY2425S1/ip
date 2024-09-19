package pixy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pixy.tasks.Event;

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
        // used ChatGPT to add more test to help validate the dates
        try {
            new Event("Invalid Event", "invalid date", "invalid date");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Invalid date format: invalid date or invalid date. Please use " +
                    "'d/M/yyyy HHmm' or 'MMM d yyyy, h:mm a'."));
        }
    }

    @Test
    public void testEdgeCaseDate() {
        // used ChatGPT to add more test to help validate the dates
        Event event = new Event("Midnight Event", "31/12/2024 2359", "01/01/2025 0001");
        assertEquals("Dec 31 2024, 11:59 pm", event.getFrom());
        assertEquals("Jan 1 2025, 12:01 am", event.getTo());
    }

}
