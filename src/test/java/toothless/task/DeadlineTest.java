package toothless.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import toothless.exceptions.ToothlessExceptions;



/**
 * Tests for Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests if the deadline is created correctly.
     *
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    @Test
    public void testDeadlineCreation() throws ToothlessExceptions {
        Deadline deadline = new Deadline("Submit assignment", "12/12/2023 2359");
        assertEquals("Submit assignment", deadline.description);
        assertEquals(LocalDateTime.of(2023, 12, 12, 23, 59), deadline.deadline);
    }

    /**
     * Tests if the deadline is created correctly with a different date and time format.
     */
    @Test
    public void testDeadlineCreationInvalidFormat() {
        Exception exception = assertThrows(ToothlessExceptions.class, () -> {
            new Deadline("Submit assignment", "12-12-2023 25:00");
        });
        String expectedMessage = "Please enter a valid date and time\n"
                + "in the format: dd/MM/yyyy HHmm or dd-MM-yyyy HHmm\n"
                + "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    /**
     * Tests if the deadline is created correctly with a different date and time format.
     *
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    @Test
    public void testToFileString() throws ToothlessExceptions {
        Deadline deadline = new Deadline("Submit assignment", "12/12/2023 2359");
        assertEquals("D | 0 | Submit assignment | 12/12/2023 2359", deadline.toFileString());
    }

    /**
     * Tests if the deadline is created correctly with a different date and time format.
     *
     * @throws ToothlessExceptions if the date and time format is invalid
     */
    @Test
    public void testToString() throws ToothlessExceptions {
        Deadline deadline = new Deadline("Submit assignment", "12/12/2023 2359");
        assertEquals("[D][ ] Submit assignment (by: 12 Dec 2023 23:59)", deadline.toString());
    }
}
