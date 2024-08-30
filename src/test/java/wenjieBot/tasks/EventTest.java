package wenjieBot.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Project Meeting", "2023-08-31 10:00", "2023-08-31 12:00");
    }

    @Test
    public void testToPrettierString() {
        String expected = "E | 0 | Project Meeting/from: 2023-08-31 10:00/to: 2023-08-31 12:00";
        assertEquals(expected, event.toPrettierString());
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] Project Meeting(from: 2023-08-31 10:00to: 2023-08-31 12:00)";
        assertEquals(expected, event.toString());
    }
}
