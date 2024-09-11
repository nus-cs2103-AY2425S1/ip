import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import task.Deadline;

public class DeadlineTest {
    @Test
    public void testNotCompletedDeadlineToEasyString() {
        LocalDateTime t = LocalDateTime.of(2024, 12, 8, 10, 0);
        Deadline d = new Deadline("something", t);
        assertEquals(d.toEasyString(), "[D][ ] something (by: " + t.toString() + ")");
    }

    @Test
    public void testCompletedDeadlineToEasyString() {
        LocalDateTime t = LocalDateTime.of(2024, 12, 8, 10, 0);
        Deadline d = new Deadline("something", t);
        d.markAsCompleted();
        assertEquals(d.toEasyString(), "[D][X] something (by: " + t.toString() + ")");
    }

    @Test
    public void testCompletedDeadlineToString() {
        LocalDateTime t = LocalDateTime.of(2024, 12, 8, 10, 0);
        Deadline d = new Deadline("something", t);
        d.markAsCompleted();
        assertEquals(d.toString(), "[D][X] something (by: 10am on DECEMBER 8, 2024)");
    }

    @Test
    public void testNotCompletedDeadlineToString() {
        LocalDateTime t = LocalDateTime.of(2024, 12, 8, 10, 0);
        Deadline d = new Deadline("something", t);
        assertEquals(d.toString(), "[D][ ] something (by: 10am on DECEMBER 8, 2024)");
    }
}
