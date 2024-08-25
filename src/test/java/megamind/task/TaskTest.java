package megamind.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testConstructor() {
        Task task = new Task("Test task");
        assertEquals("[❌] Test task", task.toString(), "Task description should be 'Test task'");
    }

    @Test
    public void testToString() {
        Task task = new Task("Test task");
        assertEquals("[❌] Test task", task.toString(), "toString should return 'Test task'");
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test task");
        task.markAsDone();
        assertEquals("[✔️] Test task", task.toString(), "Task should be marked as done");
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new Task("Test task");
        task.markAsDone();
        task.markAsNotDone();
        assertEquals("[❌] Test task", task.toString(), "Task should be marked as not done");
    }
}
