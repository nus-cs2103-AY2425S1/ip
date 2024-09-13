package JayTest.task;

import jay.task.Task;
import jay.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        ToDoTask task = new ToDoTask("read book", false, Task.Priority.Low);
        assertEquals("[T][ ] read book { Priority: Low }", task.toString());

        task.markAsDone();
        assertEquals("[T][X] read book { Priority: Low }", task.toString());

        task.markAsNotDone();
        assertEquals("[T][ ] read book { Priority: Low }", task.toString());
    }

    @Test
    public void testSimpleFormat() {
        ToDoTask task = new ToDoTask("read book", false, Task.Priority.Low);
        assertEquals("T | 0 | read book | Low", task.getSimpleFormat());

        task.markAsDone();
        assertEquals("T | 1 | read book | Low", task.getSimpleFormat());

        task.markAsNotDone();
        assertEquals("T | 0 | read book | Low", task.getSimpleFormat());
    }
}
