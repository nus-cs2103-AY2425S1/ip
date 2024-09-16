package task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testDeadlineMarking() {
        Deadline deadline = new Deadline("Submit assignment", LocalDate.parse("2023-10-10"));
        deadline.mark();
        assertTrue(deadline.isDone());
    }

    /**
     * Tests unmarking a Deadline task as not done.
     */
    @Test
    public void testDeadlineUnmarking() {
        Deadline deadline = new Deadline("Submit assignment", LocalDate.parse("2023-10-10"));
        deadline.mark();
        deadline.unmark();
        assertFalse(deadline.isDone());
    }
}
