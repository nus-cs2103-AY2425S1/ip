package lama.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void InitialStatusTest() {
        Task task = new Todo("Test Task");
        assertFalse(task.isDone);
    }

    @Test
    public void markAsDoneTest() {
        Task task = new Todo("Test Task");
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void markAsUnDoneTest() {
        Task task = new Todo("Test Task");
        task.markAsDone();
        task.markAsUnDone();
        assertFalse(task.isDone);
    }
    @Test
    public void getStatusIconTest() {
        Task task = new Todo("Test Task");
        String ans = task.getStatusIcon();
        assertFalse(task.isDone);
        assertEquals(" ", ans);
    }

    @Test
    public void getStatusIconDoneTest() {
        Task task = new Todo("Test Task");
        task.markAsDone();
        String ans = task.getStatusIcon();
        assertTrue(task.isDone);
        assertEquals("X", ans);
    }

}
