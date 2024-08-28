package skd.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("submit report", "2024-08-30 18:00");
        assertEquals("[D][ ] submit report (by: Aug 30 2024, 18:00)", deadline.toString());
    }

    @Test
    public void testMarkAsDone() {
        Deadline deadline = new Deadline("submit report", "2024-08-30 18:00");
        deadline.markAsDone();
        assertTrue(deadline.isDone());
        assertEquals("[D][X] submit report (by: Aug 30 2024, 18:00)", deadline.toString());
    }

    @Test
    public void testDeadlineWithIsDone() {
        Deadline deadline = new Deadline("submit report", "2024-08-30 18:00", true);
        assertTrue(deadline.isDone());
        assertEquals("[D][X] submit report (by: Aug 30 2024, 18:00)", deadline.toString());
    }
}