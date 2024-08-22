package tina.task;

import org.junit.jupiter.api.Test;
import tina.TinaException;
import tina.task.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void eventDescriptionTest() {
        Event e = new Event("project meeting", "2/12/2019 1800", "2/12/2019 1900");
        assertEquals("[E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 02 2019 19:00)", e.getDes());
    }

    @Test
    public void eventTest() {
        Event e = new Event("project meeting", "2/12/2019 1800", "2/12/2019 1900");
        assertEquals("E 0 project meeting | 2019-12-02T18:00 | 2019-12-02T19:00", e.toString());
    }

    @Test
    public void eventTestException() {
        try {
            Event e = new Event("project meeting", "2/12/201 1800", "2/12/2019 1900");
            assertEquals("E 0 project meeting | 2019-12-02T18:00 | 2019-12-02T19:00", e.toString());
            fail();
        } catch (TinaException e) {
            assertEquals("Invalid date and time format", e.getMessage());
        }
    }
}
