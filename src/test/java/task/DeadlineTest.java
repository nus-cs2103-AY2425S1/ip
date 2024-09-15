package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void testParseDateWithGivenDate() {
        Deadline deadline = new Deadline();
        String input = "7/08/2024";
        LocalDate expected = LocalDate.parse("2024-08-07", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate actual = deadline.parseDate(input);
        assertEquals(expected, actual);
    }

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
