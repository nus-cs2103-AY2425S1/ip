package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Deadline class.
 * This class tests the functionality of parsing and loading deadlines.
 */
public class DeadlineTest {

    /**
     * Tests if the parseDate method in the Deadline class correctly parses a given date string.
     * It checks whether the expected LocalDate matches the actual LocalDate returned by the method.
     */
    @Test
    public void testParseDateWithGivenDate() {
        Deadline deadline = new Deadline();
        String input = "7/08/2024";
        LocalDate expected = LocalDate.parse("2024-08-07", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate actual = deadline.parseDate(input);
        assertEquals(expected, actual);
    }

    /**
     * Tests if the convertSavedDataToTask method in the Deadline class correctly loads
     * a saved deadline from an array of strings representing task data.
     * It checks that the task description, status, and due date are correctly loaded.
     */
    @Test
    public void testLoadingSavedDeadline() {
        Deadline deadline = new Deadline();
        String[] input = new String[] {"D", "1", "finish homework", "2024-08-29"};
        deadline.convertSavedDataToTask(input);
        assertEquals("finish homework", deadline.description);
        assertTrue(deadline.isDone);
        assertEquals(LocalDate.parse("2024-08-29"), deadline.dueTime);
    }
}
