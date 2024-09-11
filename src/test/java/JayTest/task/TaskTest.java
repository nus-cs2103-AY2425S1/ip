package JayTest.task;

import jay.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        Task task = new Task("test", false, Task.Priority.Low);
        assertEquals("[ ] test { Priority: Low }", task.toString());

        task.markAsDone();
        assertEquals("[X] test { Priority: Low }", task.toString());

        task.markAsNotDone();
        assertEquals("[ ] test { Priority: Low }", task.toString());
    }

    @Test
    public void testSimpleFormat() {
        Task task = new Task("test", false, Task.Priority.Low);
        assertEquals("0 | test | Low", task.getSimpleFormat());

        task.markAsDone();
        assertEquals("1 | test | Low", task.getSimpleFormat());

        task.markAsNotDone();
        assertEquals("0 | test | Low", task.getSimpleFormat());
    }
}
