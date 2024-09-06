package rex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testFormattedNotDone() {
        assertEquals("E | 0 | team meeting | 01-11-24 1200 | 01-11-24 1400",
                new Event("team meeting", false, LocalDateTime.of(2024, 11, 1, 12, 0),
                        LocalDateTime.of(2024, 11, 1, 14, 0)).formatter());
    }

    @Test
    public void testFormattedDone() {
        assertEquals("E | 1 | team meeting | 01-11-24 1200 | 01-11-24 1400",
                new Event("team meeting", true, LocalDateTime.of(2024, 11, 1, 12, 0),
                        LocalDateTime.of(2024, 11, 1, 14, 0)).formatter());
    }

    @Test
    public void testToStringNotDone() {
        assertEquals("[E][ ] team meeting(from: 01 Nov 2024 1200HRS | to: 01 Nov 2024 1400HRS)",
                new Event("team meeting", false, LocalDateTime.of(2024, 11, 1, 12, 0),
                        LocalDateTime.of(2024, 11, 1, 14, 0)).toString());
    }

    @Test
    public void testToStringDone() {
        assertEquals("[E][X] team meeting(from: 01 Nov 2024 1200HRS | to: 01 Nov 2024 1400HRS)",
                new Event("team meeting", true, LocalDateTime.of(2024, 11, 1, 12, 0),
                        LocalDateTime.of(2024, 11, 1, 14, 0)).toString());
    }
}
