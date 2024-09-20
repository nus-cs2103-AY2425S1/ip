package totoro.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Team meeting",
                LocalDateTime.of(2024, 9, 30, 10, 0),
                LocalDateTime.of(2024, 9, 30, 11, 0));
    }

    @Test
    public void testGetFrom() {
        assertEquals(LocalDateTime.of(2024, 9, 30, 10, 0),
                event.getFrom());
    }

    @Test
    public void testGetTo() {
        assertEquals(LocalDateTime.of(2024, 9, 30, 11, 0),
                event.getTo());
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] Team meeting (from: 30 Sep 2024 10:00 to: 30 Sep 2024 11:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToFileFormat() {
        String expected = "E | 0 | Team meeting | 30 Sep 2024 10:00 | 30 Sep 2024 11:00";
        assertEquals(expected, event.toFileFormat());
    }

    @Test
    public void testMarkAsDone() {
        event.markAsDone();
        assertTrue(event.isDone);
    }

    @Test
    public void testMarkAsNotDone() {
        event.markAsDone(); // First mark as done
        event.markAsNotDone();
        assertFalse(event.isDone);
    }
}
