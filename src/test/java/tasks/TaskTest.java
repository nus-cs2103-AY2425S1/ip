package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[ ] test", new Task("test").toString());
    }

    @Test
    public void testStatusIcon() {
        Task task = new Task("test");

        // undone task
        assertEquals(" ", task.getStatusIcon());

        // completed task
        task.isDone = true;
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkTask() {
        Task task = new Task("test");
        assertFalse(task.isDone);
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void testUnmarkTask() {
        Task task = new Task("test");
        task.isDone = true;
        assertTrue(task.isDone);
        task.markAsUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void testWriteTask() {
        // uncompleted task
        Task task = new Task("test");
        assertEquals("0,test", task.writeTask());

        // completed task
        task.isDone = true;
        assertEquals("1,test", task.writeTask());
    }
}
