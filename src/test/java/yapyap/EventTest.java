package yapyap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void event_validDate_eventCreatedSuccessfully() throws YapperBotException {
        Event event = new Event("meeting", "2/12/2024 1400", "2/12/2024 1600");
        assertEquals("meeting", event.getDescription());
        assertEquals("[E][ ] meeting (from: Dec 02 2024 2:00 pm to: Dec 02 2024 4:00 pm)",
                event.toString());
    }

    @Test
    public void event_invalidDate_throwsException() {
        Exception exception = assertThrows(YapperBotException.class, () -> {
            new Event("meeting", "random", "random!");
        });
        assertEquals(exception.getMessage(), "Invalid date-time format! Please use the format: d/M/yyyy HHmm");
    }

    @Test
    public void mark_eventMarked_eventMarkedSuccessfully() throws YapperBotException {
        Event event = new Event("meeting", "2/12/2024 1400", "2/12/2024 1600");
        assertEquals("[E][ ] meeting (from: Dec 02 2024 2:00 pm to: Dec 02 2024 4:00 pm)",
                event.toString());
        event.mark();
        assertTrue(event.getIsDone());
        assertEquals("[E][X] meeting (from: Dec 02 2024 2:00 pm to: Dec 02 2024 4:00 pm)",
                event.toString());
    }

    @Test
    public void unmark_eventUnmarked_eventUnmarkedSuccessfully() throws YapperBotException {
        Event event = new Event("meeting", "2/12/2024 1400", "2/12/2024 1600", true);
        event.unmark();
        assertFalse(event.getIsDone());
        assertEquals("[E][ ] meeting (from: Dec 02 2024 2:00 pm to: Dec 02 2024 4:00 pm)",
                event.toString());
    }
}
