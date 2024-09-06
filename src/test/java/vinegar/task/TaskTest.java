package vinegar.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Task class functionality.
 * <p>
 * This class covers tests for marking tasks as done and the toString representation of tasks.
 */
public class TaskTest {

    /**
     * Tests marking a task as done.
     */
    @Test
    public void testMarkAsDone() {
        Task task = new Todo("Read book");
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done.");
    }

    /**
     * Tests the string representation of a task before and after marking it as done.
     */
    @Test
    public void testToString() {
        Task task = new Todo("Read book");
        assertEquals("[T][ ] Read book", task.toString());
        task.markAsDone();
        assertEquals("[T][X] Read book", task.toString());
    }
}
