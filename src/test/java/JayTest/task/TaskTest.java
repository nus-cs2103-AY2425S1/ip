package JayTest.task;

import jay.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        Task task = new Task("test", false);
        assertEquals("[ ] test", task.toString());

        task.markAsDone();
        assertEquals("[X] test", task.toString());

        task.markAsNotDone();
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void testSimpleFormat() {
        Task task = new Task("test", false);
        assertEquals("0 | test", task.getSimpleFormat());

        task.markAsDone();
        assertEquals("1 | test", task.getSimpleFormat());

        task.markAsNotDone();
        assertEquals("0 | test", task.getSimpleFormat());
    }
}
