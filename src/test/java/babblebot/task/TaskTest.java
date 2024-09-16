package babblebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
