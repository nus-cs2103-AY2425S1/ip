package bellroy;

import bellroy.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("homework", "2024-09-27 1800");
        assertEquals("[D][ ] homework (by: Sep 27 2024, 6:00 pm)", deadline.toString());
    }

    @Test
    public void testMarkDone() {
        Deadline deadline = new Deadline("homework", "2024-09-27 1800");
        deadline.markDone();
        assertEquals("[D][X] homework (by: Sep 27 2024, 6:00 pm)", deadline.toString());
    }
}
