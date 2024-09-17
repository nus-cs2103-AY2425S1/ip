package sentinel.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import sentinel.SentinelException;

public class TaskListTest {
    @Test
    public void markAsDone_validNumber_success() {
        TaskList newTaskList = new TaskList();
        newTaskList.addTask(new Todo("Test"));

        try {
            assertTrue(newTaskList.markAsDone(1).getStatus());
        } catch (SentinelException e) {
            fail();
        }
    }

    @Test
    public void markAsUndone_validNumber_success() {
        TaskList newTaskList = new TaskList();
        newTaskList.addTask(new Todo("Test"));

        try {
            newTaskList.markAsDone(1);
            assertFalse(newTaskList.markAsUndone(1).getStatus());
        } catch (SentinelException e) {
            fail();
        }
    }

    @Test
    public void markAsDone_invalidNumber_exceptionThrown() {
        TaskList newTaskList = new TaskList();
        newTaskList.addTask(new Todo("Test"));

        try {
            newTaskList.markAsDone(2);
            newTaskList.markAsUndone(2);
            assertFalse(newTaskList.markAsDone(2).getStatus());
            fail();
        } catch (SentinelException e) {
            assertEquals("That task number does not exist!", e.getMessage());
        }
    }

    @Test
    public void markAsUndone_invalidNumber_exceptionThrown() {
        TaskList newTaskList = new TaskList();
        newTaskList.addTask(new Todo("Test"));

        try {
            newTaskList.markAsDone(2);
            newTaskList.markAsUndone(2);
            assertFalse(newTaskList.markAsDone(2).getStatus());
            fail();
        } catch (SentinelException e) {
            assertEquals("That task number does not exist!", e.getMessage());
        }
    }
}
