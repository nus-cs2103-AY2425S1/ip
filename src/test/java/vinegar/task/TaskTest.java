package vinegar.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Todo("Read book");
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done.");
    }

    @Test
    public void testToString() {
        Task task = new Todo("Read book");
        assertEquals("[T][ ] Read book", task.toString());
        task.markAsDone();
        assertEquals("[T][X] Read book", task.toString());
    }
}
