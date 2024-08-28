package stelle;

import org.junit.jupiter.api.Test;
import stelle.exception.WrongDateFormatException;
import stelle.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents a class used for testing Stelle.java.
 * @author Lee Ze Hao (A0276123J)
 */

public class DeadlineTest {
    @Test
    public void toString_constructorParams_correctNameAndDate() {
        Deadline deadline = new Deadline("A deadline", "2024-08-29 05:27");
        assertEquals("[D][ ] A deadline (by: 29 August 2024 05:27)", deadline.toString());
    }

    @Test
    public void getByTime_wellFormattedDate_correctDateString() {
        Deadline deadline = new Deadline("A deadline", "2024-08-29 05:27");
        assertEquals("2024-08-29 05:27", deadline.getByTime());
    }

    @Test
    public void getByTime_badlyFormattedDate_throwsCorrectException() {
        assertThrows(WrongDateFormatException.class, () -> new Deadline("A deadline", "2024-08 5am"));
    }
}
