package friendlybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A JUnit test for Task.
 */
public class TaskTest {
    /**
     * Tests the String representation of a completed Task.
     */
    @Test
    public void createTask_markCompleted_correctStringOutput() {
        Task task = new Task("test task");
        task.markAsDone();
        assertEquals("[X] test task", task.toString());
    }

    /**
     * Tests the String representation of an incomplete Task.
     */
    @Test
    public void createTask_incomplete_correctStringOutput() {
        Task task = new Task("test task");
        assertEquals("[ ] test task", task.toString());
    }
}
