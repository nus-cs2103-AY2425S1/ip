package jeriel.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("Project meeting", "2024-09-01T14:00", "2024-09-01T16:00");
        assertEquals("[E][ ] Project meeting (from: 2024-09-01T14:00 to: 2024-09-01T16:00)", event.toString());
    }
}
