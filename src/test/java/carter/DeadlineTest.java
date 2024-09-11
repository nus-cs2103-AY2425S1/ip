package carter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline deadline;

    @Test
    public void test_deadline_constructor() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2025, 1, 1, 16, 0);
        deadline = new Deadline("test task", deadlineDateTime);
        assertFalse(deadline.isDone);

        deadline = new Deadline("test task", deadlineDateTime, true);
        assertTrue(deadline.isDone);
    }

    @Test
    public void testStringConversion() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2025, 1, 1, 16, 0);
        deadline = new Deadline("test task", deadlineDateTime);
        String actualOutput = deadline.toString();
        String expectedOutput = "[D][ ] test task (by: Jan 01 2025 16:00)";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testToFileString() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2025, 1, 1, 16, 0);
        deadline = new Deadline("test task", deadlineDateTime);
        String actualOutput = deadline.toFileString();
        String expectedOutput = "D | 0 | test task |2025-01-01 16:00";
        assertEquals(expectedOutput, actualOutput);
    }
}
