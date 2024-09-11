package rizz.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

class EventTest {

    @Test
    public void testToString() {
        LocalDateTime from1 = LocalDateTime.of(2023, 9, 10, 14, 30);
        LocalTime to1 = LocalTime.of(16, 0);

        LocalDateTime from2 = LocalDateTime.of(2023, 9, 10, 9, 30);
        LocalTime to2 = LocalTime.of(18, 0);

        Event event1 = new Event("Team meeting", from1, to1, true);
        Event event2 = new Event("Team meeting", from2, to2, false);

        String str1 = event1.toString();
        String str2 = event2.toString();

        assertEquals("[E][X] Team meeting (at: Sep 10 2023 1430-1600)", str1);
        assertEquals("[E][ ] Team meeting (at: Sep 10 2023 0930-1800)", str2);
    }

    @Test
    public void testMarkUnmark() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 14, 30);
        LocalTime to = LocalTime.of(16, 0);
        Event event = new Event("Team meeting", from, to, false);

        event.markAsDone();

        assertTrue(event.getStatus());

        event.unmarkAsDone();

        assertFalse(event.getStatus());
    }

    @Test
    public void testExport() {
        LocalDateTime from = LocalDateTime.of(2023, 9, 10, 14, 30);
        LocalTime to = LocalTime.of(16, 0);
        Event event = new Event("Team meeting", from, to, true);

        String export = event.export();

        assertEquals("E | 1 | Team meeting | 2023-09-10 1430 1600", export);
    }
}
