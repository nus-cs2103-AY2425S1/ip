package mendel.discretetask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void showDeadline() {
        assertEquals("[D][ ] testing (by: Jan 01 2030)",
                Deadline.of("deadline testing /by 1 Jan 2030").toString());
        assertEquals("[D][ ] testing one (by: Jan 01 2030, 15:00)",
                Deadline.of("deadline testing one /by 1 Jan 2030 1500").toString());
        assertEquals("[D][ ] testing two (by: Jan 01 2030)",
                Deadline.of("deadline testing two /by 01-01-2030").toString());
        assertEquals("[D][ ] testing three (by: Jan 01 2030, 15:00)",
                Deadline.of("deadline testing three /by 01-01-2030 15:00").toString());
    }

    @Test
    public void targetDue() {
        assertTrue(Deadline.of("deadline testing /by 01-01-2030 15:00").isTargetDueDate("01 Jan 2030"));
        assertFalse(Deadline.of("deadline testing /by 01-01-2030 15:00").isTargetDueDate("02 02 2030"));
    }

    @Test
    public void noDescriptionException() {
        try {
            Deadline.of("deadline /by 1 Jan 2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline description cannot be empty.\nAdd description.", e.toString());
        }
    }

    @Test
    public void noDateException() {
        try {
            Deadline.of("deadline testing");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline due cannot be empty.\nPlease indicate a due.", e.toString());
        }
    }

    @Test
    public void noDescriptionAndDateException() {
        try {
            Deadline.of("deadline");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline needs more details.\nAdd description and due.", e.toString());
        }
    }

    @Test
    public void unspacedByException() {
        try {
            Deadline.of("deadline testing/by 1 Jan 2030");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! deadline due wrongly formatted\nPlease add spaces around /by", e.toString());
        }
    }

    @Test
    public void checkMatchingDescription() {
        assertTrue(Deadline.of("deadline CS2103T assignment /by 01-01-2030 15:00")
                .isMatchingDescription("CS2103T"));
        assertFalse(Deadline.of("deadline CS2103T assignment /by 01-01-2030 15:00")
                .isMatchingDescription("CS2104T"));
    }

    @Test
    public void reminder() {
        Deadline deadline = Deadline.of("deadline CS2103T assignment /by 01-01-2030 15:00");
        assertTrue(deadline.isIncompleteWithinTargetDueDate("02-02-2031"));
        assertFalse(deadline.isIncompleteWithinTargetDueDate("02-02-2001"));
        deadline.markAsDone();
        assertFalse(deadline.isIncompleteWithinTargetDueDate("02-02-2031"));

    }

    @Test
    public void invalidDateSet() {
        try {
            Deadline.of("deadline CS2103T assignment /by 01-01-2023 15:00");
            fail();
        } catch (Exception e) {
            assertEquals("OOPS! Start day is later than today.\nPlease ensure valid time period.",
                    e.toString());
        }
    }
}
