package stelle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import stelle.exception.WrongDateFormatException;
import stelle.task.Deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        Deadline deadline = new Deadline(
                "A deadline",
                LocalDateTime.of(LocalDate.of(2024, 8, 29), LocalTime.of(5,27))
        );
        assertEquals("[D][ ] A deadline (by: 29 August 2024 05:27)", deadline.toString());
        Deadline deadline2 = new Deadline(
                "A second deadline",
                LocalDateTime.of(LocalDate.of(2024, 9, 20), LocalTime.of(20,30))
        );
        assertEquals("[D][ ] A second deadline (by: 20 September 2024 20:30)", deadline2.toString());
        Deadline deadline3 = new Deadline(
                "A third     deadline with a big space",
                LocalDateTime.of(LocalDate.of(2040, 1, 5), LocalTime.of(0,0))
        );
        assertEquals("[D][ ] A third     deadline with a big space (by: 5 January 2040 00:00)", deadline3.toString());
    }

    /**
     * Tests getByTime() method and date/time storage and checks for correct date string returned.
     */
    @Test
    public void getByTime_wellFormattedDate_correctDateString() {
        Deadline deadline = new Deadline(
                "A deadline",
                LocalDateTime.of(LocalDate.of(2024, 8, 29), LocalTime.of(5,27))
        );
        assertEquals("2024-08-29 05:27", deadline.getByTime());
        Deadline deadline2 = new Deadline(
                "A deadline",
                LocalDateTime.of(LocalDate.of(2024, 9, 20), LocalTime.of(20,30))
        );
        assertEquals("2024-09-20 20:30", deadline2.getByTime());
        Deadline deadline3 = new Deadline(
                "A deadline",
                LocalDateTime.of(LocalDate.of(2040, 1, 5), LocalTime.of(0,0))
        );
        assertEquals("2040-01-05 00:00", deadline3.getByTime());
    }
}
