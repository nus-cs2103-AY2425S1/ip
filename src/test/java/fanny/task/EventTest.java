package fanny.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

    private Event event;
    private LocalDateTime from;
    private LocalDateTime to;

    @BeforeEach
    public void setUp() {
        from = LocalDateTime.of(2024, 12, 12, 12, 0);
        to = LocalDateTime.of(2024, 12, 12, 13, 0);
        event = new Event("Dinner", from, to);
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] Dinner(from: Dec 12, 2024 12:00 to: Dec 12, 2024 13:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testGetFrom() {
        assertEquals("Dec 12, 2024 12:00", event.getFrom());
    }

    @Test
    public void testGetTo() {
        assertEquals("Dec 12, 2024 13:00", event.getTo());
    }

}

