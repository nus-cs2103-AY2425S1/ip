package JayTest.task;

import jay.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        ToDoTask task = new ToDoTask("read book", false);
        assertEquals("[T][ ] read book", task.toString());

        task.markAsDone();
        assertEquals("[T][X] read book", task.toString());

        task.markAsNotDone();
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void testSimpleFormat() {
        ToDoTask task = new ToDoTask("read book", false);
        assertEquals("T | 0 | read book", task.getSimpleFormat());

        task.markAsDone();
        assertEquals("T | 1 | read book", task.getSimpleFormat());

        task.markAsNotDone();
        assertEquals("T | 0 | read book", task.getSimpleFormat());
    }
}
