package megamind.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void constructor_success() {
        try {
            new Event("project meeting", "11/11/1111 1111", "22/12/2222 2222");
        } catch (Exception e) {
            assertEquals("Text '11/11/1111 1111' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDateTimeFormat_exceptionThrown() {
        try {
            new Event("project meeting", "test", "22/12/2222 2222");
        } catch (Exception e) {
            assertEquals("Text 'test' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void testToString() {
        Event event = new Event("project meeting", "11/11/1111 1111", "22/12/2222 2222");
        assertEquals("[E][ ] project meeting (FROM: Nov 11, 1111, 11:11:00 AM TO: Dec 22, 2222, 10:22:00 PM)",
                event.toString());
    }
}
