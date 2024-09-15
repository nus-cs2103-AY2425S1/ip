package patrick.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testCreateValidEvent() {
        Event event = new Event("Project meeting", "2023-10-15 1200", "1400");
        assertNotNull(event);
        assertEquals("Project meeting", event.getDescription());
    }

    @Test
    public void testToStringFormat() {
        Event event = new Event("Project meeting", "2023-10-15 1200", "1400");
        assertEquals("E | O | Project meeting | Oct 15 2023 1200-1400", event.toString());
    }
}
