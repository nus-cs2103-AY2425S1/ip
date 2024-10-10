import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;
import dave.task.Deadline;




/**
 * Test class for {@code Deadline}.
 */
public class DeadlineTest {

    @Test
    public void testDeadlineCreation_validDateTime() throws InvalidDescriptionException {
        Deadline deadline = new Deadline("Submit report /by 2024-08-02 1800");

        LocalDate expectedDate = LocalDate.of(2024, 8, 2);
        LocalTime expectedTime = LocalTime.of(18, 0);
        LocalDateTime expectedDateTime = LocalDateTime.of(expectedDate, expectedTime);

        assertEquals(expectedDateTime, deadline.getDueDateTime());
    }

    @Test
    public void testDeadlineCreation_invalidDate() {
        assertThrows(InvalidDateTimeFormatException.class, () -> {
            new Deadline("Submit report /by 02-08-2024 1800");
        });
    }

    @Test
    public void testDeadlineCreation_invalidTime() {
        assertThrows(InvalidDateTimeFormatException.class, () -> {
            new Deadline("Submit report /by 2024-08-02 18:00");
        });
    }

    @Test
    public void testDeadlineCreation_missingByKeyword() {
        assertThrows(InvalidDescriptionException.class, () -> {
            new Deadline("Submit report 2024-08-02 1800"); // Missing '/by'
        });
    }

    @Test
    public void testDeadlineToString()
            throws InvalidDescriptionException, InvalidDateTimeFormatException {
        Deadline deadline = new Deadline("Submit report /by 2024-08-02 1800");
        String expectedString = "[D][ ] Submit report (by: Aug 02 2024 18:00)";
        assertEquals(expectedString, deadline.toString());

        deadline.markAsDone();
        expectedString = "[D][X] Submit report (by: Aug 02 2024 18:00)";
        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void testDeadlineWrite()
            throws InvalidDescriptionException, InvalidDateTimeFormatException {
        Deadline deadline = new Deadline("Submit report /by 2024-08-02 1800");
        String expectedOutput = "D | 0 | Submit report | Aug 02 2024 1800\n";
        assertEquals(expectedOutput, deadline.write());

        deadline.markAsDone();
        expectedOutput = "D | 1 | Submit report | Aug 02 2024 1800\n";
        assertEquals(expectedOutput, deadline.write());
    }
}
