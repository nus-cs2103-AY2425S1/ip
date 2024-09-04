package blacknut.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToFileFormat() {
        Event event = new Event("Project meeting", "2024-09-10 14:00", "2024-09-10 16:00");
        String expectedOutput = "E | 0 | Project meeting | 2024-09-10 14:00 | 2024-09-10 16:00";
        assertEquals(expectedOutput, event.toFileFormat());
    }

    @Test
    public void testToString() {
        Event event = new Event("Project meeting", "2024-09-10 14:00", "2024-09-10 16:00");
        String expectedOutput = "[E][ ] Project meeting (from: Sep 10 2024, 14:00 to: Sep 10 2024, 16:00)";
        assertEquals(expectedOutput, event.toString());
    }
}
