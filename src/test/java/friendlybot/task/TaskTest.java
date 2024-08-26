package friendlybot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void createTask_markCompleted_correctStringOutput() {
        Task task = new Task("test task");
        task.markAsDone();
        assertEquals("[X] test task", task.toString());
    }

    @Test
    public void createTask_incomplete_correctStringOutput() {
        Task task = new Task("test task");
        assertEquals("[ ] test task", task.toString());
    }
}
