package beeboo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import beeboo.exception.InvalidDateException;

/**
 * Tests for the Events class.
 */
class EventsTest {

    /*
     * Tests if missing the 'from' keyword throws InvalidDateException.
     */
    @Test
    void testCreateEventMissingFromKeyword() {
        String input = "Team Meeting /May 15 2024 at 10:00 AM to May 15 2024 at 11:00 AM";
        assertThrows(InvalidDateException.class, () -> Events.createEvent(input));
    }


    /**
     * Tests if the event's save format is correct.
     */
    @Test
    void testSaveFormat() {
        LocalDateTime startDate = LocalDateTime.of(2024, 5, 15, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 15, 11, 0);
        Events event = new Events("Team Meeting", startDate, endDate);

        String expected = "E | 0 | Team Meeting | 2024-05-15T10:00 | 2024-05-15T11:00";
        assertEquals(expected, event.saveFormat());
    }

}
