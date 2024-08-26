package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testConstructorValidDates() {
        try {
            Event event = new Event("Team meeting", "2024-09-01", "2024-09-01");
            assertEquals("Team meeting", event.description);
            assertEquals(LocalDate.of(2024, 9, 1), event.start);
            assertEquals(LocalDate.of(2024, 9, 1), event.end);
            assertFalse(event.isDone);

        } catch (EventException e) {
            fail("EventException should not be thrown for valid dates.");

        }
    }

    @Test
    void testConstructorInvalidStartDate() {
        assertThrows(EventException.class, () -> {
            new Event("Conference", "invalid-start-date", "2024-11-10");

        });
    }

    @Test
    void testConstructorInvalidEndDate() {
        assertThrows(EventException.class, () -> {
            new Event("Workshop", "2024-11-10", "invalid-end-date");

        });
    }

    @Test
    void testMarkEvent() {
        try {
            Event event = new Event("Project kickoff", "2024-08-20", "2024-08-20");
            event.mark();
            assertTrue(event.isDone);
            assertEquals("[E][X] Project kickoff (from: 20 Aug 2024 to: 20 Aug 2024)", event.toString());

        } catch (EventException e) {
            fail("EventException should not be thrown for valid dates.");

        }
    }

    @Test
    void testUnmarkEvent() {
        try {
            Event event = new Event("Annual review", "2024-12-01", "2024-12-02");
            event.mark();  // Mark as done
            event.unmark();  // Then unmark
            assertFalse(event.isDone);
            assertEquals("[E][ ] Annual review (from: 1 Dec 2024 to: 2 Dec 2024)", event.toString());

        } catch (EventException e) {
            fail("EventException should not be thrown for valid dates.");

        }
    }

    @Test
    void testToDataFormat() {
        try {
            Event event = new Event("Team building", "2024-09-15", "2024-09-16");
            String expected = "event | 0 | Team building | 2024-09-15 | 2024-09-16";
            assertEquals(expected, event.toDataFormat());
            
        } catch (EventException e) {
            fail("EventException should not be thrown for valid dates.");

        }
    }

    @Test
    void testToString() {
        try {
            Event event = new Event("Hackathon", "2024-11-01", "2024-11-03");
            String expected = "[E][ ] Hackathon (from: 1 Nov 2024 to: 3 Nov 2024)";
            assertEquals(expected, event.toString());

        } catch (EventException e) {
            fail("EventException should not be thrown for valid dates.");

        }
    }

    @Test
    void testToStringWithMarking() {
        try {
            Event event = new Event("Client presentation", "2024-10-05", "2024-10-05");
            event.mark();
            String expected = "[E][X] Client presentation (from: 5 Oct 2024 to: 5 Oct 2024)";

            assertEquals(expected, event.toString());
        } catch (EventException e) {
            fail("EventException should not be thrown for valid dates.");

        }
    }
}
