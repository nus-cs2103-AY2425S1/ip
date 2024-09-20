package totoro.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        deadline = new Deadline("Submit assignment",
                LocalDateTime.of(2024, 9, 30, 12, 0));
    }

    @Test
    public void testGetDeadline() {
        assertEquals(LocalDateTime.of(2024, 9, 30, 12, 0), deadline.getDeadline());
    }

    @Test
    public void testToString() {
        String expected = "[D][ ] Submit assignment (by: 30 Sep 2024 12:00)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToFileFormat() {
        String expected = "D | 0 | Submit assignment | 30 Sep 2024 12:00";
        assertEquals(expected, deadline.toFileFormat());
    }

    @Test
    public void testMarkAsDone() {
        deadline.markAsDone();
        assertTrue(deadline.isDone);
    }

    @Test
    public void testMarkAsNotDone() {
        deadline.markAsDone(); // First mark as done
        deadline.markAsNotDone();
        assertFalse(deadline.isDone);
    }
}
