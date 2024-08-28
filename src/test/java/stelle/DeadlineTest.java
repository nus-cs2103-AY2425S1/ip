package stelle;

import org.junit.jupiter.api.Test;
import stelle.exception.WrongDateFormatException;
import stelle.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents a class used for testing Deadline.java.
 * @author Lee Ze Hao (A0276123J)
 */

public class DeadlineTest {
    /**
     * Tests toString() method and checks for correct string returned.
     */
    @Test
    public void toString_constructorParams_correctNameAndDate() {
        Deadline deadline = new Deadline("A deadline", "2024-08-29 05:27");
        assertEquals("[D][ ] A deadline (by: 29 August 2024 05:27)", deadline.toString());
    }

    /**
     * Tests getByTime() method and date/time storage and checks for correct date string returned.
     */
    @Test
    public void getByTime_wellFormattedDate_correctDateString() {
        Deadline deadline = new Deadline("A deadline", "2024-08-29 05:27");
        assertEquals("2024-08-29 05:27", deadline.getByTime());
    }

    /**
     * Checks that correct exception is thrown when trying to create deadline with bad date format.
     */
    @Test
    public void getByTime_badlyFormattedDate_throwsCorrectException() {
        assertThrows(WrongDateFormatException.class, () -> new Deadline("A deadline", "2024-08 5am"));
    }
}
