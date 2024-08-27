package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testConstructorValidDate() {
        try {
            Deadline deadline = new Deadline("Finish project", "2024-12-31");
            assertEquals("Finish project", deadline.description);
            assertEquals(LocalDate.of(2024, 12, 31), deadline.by);

        } catch (DeadlineException e) {
            fail("DeadlineException should not be thrown for a valid date.");

        }
    }

    @Test
    public void testConstructorInvalidDate() {
        assertThrows(DeadlineException.class, () -> {
            new Deadline("Finish project", "invalid-date");

        });
    }

    @Test
    void testMarkDeadline() {
        try {
            Deadline deadline = new Deadline("Submit report", "2024-10-15");
            deadline.mark();
            assertTrue(deadline.isDone);
            assertEquals("[D][X] Submit report (by: 15 Oct 2024)", deadline.toString());

        } catch (DeadlineException e) {
            fail("DeadlineException should not be thrown for a valid date.");
        }
    }

    @Test
    void testUnmarkDeadline() {
        try {
            Deadline deadline = new Deadline("Submit assignment", "2024-09-20");
            deadline.mark(); // Mark as done
            deadline.unmark(); // Then unmark
            assertFalse(deadline.isDone);
            assertEquals("[D][ ] Submit assignment (by: 20 Sep 2024)", deadline.toString());

        } catch (DeadlineException e) {
            fail("DeadlineException should not be thrown for a valid date.");
        }
    }

    @Test
    public void testToDataFormat() {
        try {
            Deadline deadline = new Deadline("Submit report", "2024-10-15");
            String expected = "deadline | 0 | Submit report | 2024-10-15";
            assertEquals(expected, deadline.toDataFormat());

        } catch (DeadlineException e) {
            fail("DeadlineException should not be thrown for a valid date.");

        }
    }

    @Test
    public void testToString() {
        try {
            Deadline deadline = new Deadline("Complete assignment", "2024-11-05");
            String expected = "[D][ ] Complete assignment (by: 5 Nov 2024)";
            assertEquals(expected, deadline.toString());

        } catch (DeadlineException e) {
            fail("DeadlineException should not be thrown for a valid date.");

        }
    }

    @Test
    public void testToStringWithDifferentDateFormat() {
        try {
            Deadline deadline = new Deadline("Start new project", "2024-03-19");
            String expected = "[D][ ] Start new project (by: 19 Mar 2024)";
            assertEquals(expected, deadline.toString());

        } catch (DeadlineException e) {
            fail("DeadlineException should not be thrown for a valid date.");

        }
    }

}
