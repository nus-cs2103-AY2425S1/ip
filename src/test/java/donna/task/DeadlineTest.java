package donna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import donna.DonnaException;

class DeadlineTest {

    @Test
    void testDeadlineInitialisationWithValidData() throws DonnaException {
        Deadline deadline = new Deadline("Return books", "30/08/2024 2359");
        assertEquals("[D][ ] Return books (by: Aug 30 2024, 11:59pm)", deadline.toString());
        assertFalse(deadline.isDone());
    }

    @Test
    void testDeadlineInitialisationWithEmptyDeadline() {
        DonnaException exception = assertThrows(DonnaException.class, () ->
                        new Deadline("Return books", ""),
                "Expected to throw DonnaException for empty deadline.");

        assertEquals("Please provide a deadline for this task!" + "\n" + "Use /by to provide a deadline.",
                exception.getMessage());
    }

    @Test
    void testDeadlineInitialisationWithInvalidDateFormat() {
        DonnaException exception = assertThrows(DonnaException.class, () -> new Deadline("Return books",
                        "30-08-2024 2359"), "Expected to throw DonnaException for invalid date format.");

        assertEquals("Invalid date and time format! Please use dd/MM/yyyy HHmm (24hr format)",
                exception.getMessage());
    }

    @Test
    void testMarkingDeadlineAsDone() throws DonnaException {
        Deadline deadline = new Deadline("Return books", "30/08/2024 2359");
        deadline.markDone();
        assertTrue(deadline.isDone());
        assertEquals("[D][X] Return books (by: Aug 30 2024, 11:59pm)", deadline.toString());
    }

    @Test
    void testToFileFormat() throws DonnaException {
        Deadline deadline = new Deadline("Return books", "30/08/2024 2359");
        assertEquals("D | 0 | Return books | 30/08/2024 2359", deadline.toFileFormat());

        // Mark as done and check
        deadline.markDone();
        assertEquals("D | 1 | Return books | 30/08/2024 2359", deadline.toFileFormat());
    }
}
