package zbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTask() {
        Task task = new Task("read book");
        assertEquals("[ ] read book", task.toString());
    }

    @Test
    public void testTaskMarkAsDone() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("[X] read book", task.toString());
    }

    @Test
    public void testTaskMarkAsUndone() {
        Task task = new Task("read book");
        task.markAsDone();
        task.markAsUndone();
        assertEquals("[ ] read book", task.toString());
    }

    @Test
    public void testTaskGetStatusIcon() {
        Task task = new Task("read book");
        assertEquals(" ", task.getStatusIcon());
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }
}
