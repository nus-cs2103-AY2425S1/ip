package lunabot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.of(2024, 9, 10, 0, 0);
        Deadline deadline = new Deadline("sleep", by);
        assertEquals("[D][ ] sleep (by: Sep 10 2024, 12:00 am)", deadline.toString());

        Deadline completedDeadline = new Deadline("sleep", by, true);
        assertEquals("[D][X] sleep (by: Sep 10 2024, 12:00 am)", completedDeadline.toString());
    }

    @Test
    public void testToFileFormat() {
        LocalDateTime by = LocalDateTime.of(2024, 9, 10, 17, 0);
        Deadline deadline = new Deadline("submit quiz", by);
        assertEquals("D | 0 | submit quiz | 2024-09-10 17:00", deadline.toFileFormat());

        Deadline completedDeadline = new Deadline("submit quiz", by, true);
        assertEquals("D | 1 | submit quiz | 2024-09-10 17:00", completedDeadline.toFileFormat());
    }
}
