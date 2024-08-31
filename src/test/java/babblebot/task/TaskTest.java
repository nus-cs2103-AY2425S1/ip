package babblebot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testMarkAsDone() {
        Task task = new Task("Test task");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void testToString() {
        Task task = new Task("Test task");
        assertEquals("[ ] Test task", task.toString());
        task.markAsDone();
        assertEquals("[X] Test task", task.toString());
    }
}
