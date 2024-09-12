package wolfie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testValidEvent() {
        LocalDateTime from = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 10, 1, 12, 0);
        Event event = new Event("Test Event", from, to, false);
        assertEquals("Test Event", event.getDescription());
        assertEquals(from, event.getFrom());
        assertEquals(to, event.getTo());
    }
}
