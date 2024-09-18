package mendel.discretetask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void showDeadline() {
        assertEquals("[E][ ] testing (from: Jan 01 2030 to Jan 01 2030)",
                Event.of("event testing /from 1 Jan 2030 /to 1 Jan 2030").toString());
        assertEquals("[E][ ] testing two (from: Jan 01 2030, 15:00 to Jan 01 2030, 15:00)",
                Event.of("event testing two /from 1 Jan 2030 1500 /to 1 Jan 2030 1500").toString());
        assertEquals("[E][ ] testing three (from: Jan 01 2030 to Jan 01 2030)",
                Event.of("event testing three /from 01-01-2030 /to 01-01-2030").toString());
        assertEquals("[E][ ] testing four (from: Jan 01 2030, 15:00 to Jan 01 2030, 15:00)",
                Event.of("event testing four /from 01-01-2030 15:00 /to 01-01-2030 15:00").toString());
    }

    @Test
    public void noDescriptionException() {
        try {
            Event.of("event /from 1 Jan 2030 /to 1 Jan 2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! event description cannot be empty.\nAdd description.", e.toString());
        }
    }

    @Test
    public void noStartDateException() {
        try {
            Event.of("event testing /from 1 Jan 2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! I am unsure of end.\nPlease specify an end.", e.toString());
        }
    }

    @Test
    public void noEndDateException() {
        try {
            Event.of("event testing /to 1 Jan 2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! I am unsure of start.\nPlease specify only one start.", e.toString());
        }
    }

    @Test
    public void noDateException() {
        try {
            Event.of("event testing");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! I am unsure of start.\nPlease specify only one start.", e.toString());
        }
    }

    @Test
    public void checkMatchingDescription() {
        assertTrue(Event.of("event CS2103T assignment /from 01-01-2030 15:00 /to 01-01-2031")
                .isMatchingDescription("CS2103T"));
        assertFalse(Event.of("event CS2103T assignment /from 01-01-2030 15:00 /to 01-01-2031")
                .isMatchingDescription("CS2104T"));
    }

    @Test
    public void reminder() {
        Event event = Event.of("event CS2103T assignment /from 01-01-2030 15:00 /to 01-01-2031");
        assertTrue(event.isIncompleteWithinTargetDueDate("02-01-2030"));
        assertTrue(event.isIncompleteWithinTargetDueDate("02-01-2032"));
        assertFalse(event.isIncompleteWithinTargetDueDate("02-01-2001"));
        event.markAsDone();
        assertFalse(event.isIncompleteWithinTargetDueDate("02-01-2031"));
    }

    @Test
    public void invalidDateSet() {
        try {
            Event.of("event CS2103T assignment /from 01-01-2030 15:00 /to 01-01-2001");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! End day is earlier than today.\nPlease ensure valid time period.",
                    e.toString());
        }

        try {
            Event.of("event CS2103T assignment /from 01-01-2001 15:00 /to 01-01-2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Start day is earlier than today.\nPlease ensure valid time period.",
                    e.toString());
        }

        try {
            Event.of("event CS2103T assignment /from 01-01-2031 15:00 /to 01-01-2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Start day is later than end day.\nPlease ensure valid time period.",
                    e.toString());
        }
    }
}
