package JUnitTests;

import Naega.Task.Task;
import Naega.Task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Todo("Submit assignment");
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new Todo("Submit assignment");
        task.markAsDone();  // First, mark it done
        task.markAsNotDone();  // Now, mark it as not done
        assertFalse(task.isDone());
    }
}