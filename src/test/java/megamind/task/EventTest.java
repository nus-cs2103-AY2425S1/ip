package megamind.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("[E][❌] project meeting (FROM: 11 Nov 1111, 11:11:00 am TO: 22 Dec 2222, 10:22:00 pm)",
                event.toString());
    }
}
