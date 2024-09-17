package bangmang.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bangmang.exception.InvalidTaskFormatException;

import java.time.LocalDateTime;

public class TaskTest {

    @Test
    public void testMarkTask() {
        Task task = new Todo("Test Task");
        task.markTask();
        assertTrue(task.getIsDone());
    }

    @Test
    public void testUnmarkTask() {
        Task task = new Todo("Test Task");
        task.markTask();
        task.unmarkTask();
        assertFalse(task.getIsDone());
    }

    @Test
    public void testToString() {
        Task task = new Task("Test Task");
        assertEquals("[ ] Test Task", task.toString());
        task.markTask();
        assertEquals("[X] Test Task", task.toString());
    }

    @Test
    public void testReadSavedTask() throws InvalidTaskFormatException {
        String taskString = "T | 0 | Test Task";
        Task task = Task.readSavedTask(taskString);
        assertTrue(task instanceof Todo);
        assertEquals("Test Task", task.getDescription());
        assertFalse(task.getIsDone());
    }

    @Test
    public void testWriteSavedTask() throws InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        assertEquals("T | 0 | Test Task", task.writeSavedTask());
        task.markTask();
        assertEquals("T | 1 | Test Task", task.writeSavedTask());
    }
}
