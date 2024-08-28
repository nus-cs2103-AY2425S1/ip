package shoai;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventTest {

    @Test
    public void testEventConstructor() {
        Event event = new Event("Project meeting", "2024-08-30", "2024-08-31");
        assertNotNull(event);
        assertEquals("Project meeting", event.getDescription());
        assertEquals("2024-08-30", event.getFrom());
        assertEquals("2024-08-31", event.getTo());
    }

    @Test
    public void testToString() {
        Event event = new Event("Project meeting", "2024-08-30", "2024-08-31");
        String expectedString = "[E][ ] Project meeting (from: 2024-08-30 to: 2024-08-31)";
        assertEquals(expectedString, event.toString());
    }
}
