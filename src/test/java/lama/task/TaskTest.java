package lama.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Test class for Task class.
 * Contains unit test case for task class.
 */
public class TaskTest {

    /**
     * Test the initial status of any task.
     * Verifies that a task object isDone is false by default.
     */
    @Test
    public void initialStatusTest() {
        Task task = new Todo("Test Task");
        assertFalse(task.isDone);
    }

    /**
     * Test the markAsDone method
     * Verifies that calling markAsDone() on a Task will set
     * its isDone to true.
     */
    @Test
    public void markAsDoneTest() {
        Task task = new Todo("Test Task");
        task.markAsDone();
        assertTrue(task.isDone);
    }

    /**
     * Test the markAsUnDone method
     * Verifies that calling markAsDone() on a Task will set
     * its isDone to false.
     */
    @Test
    public void markAsUnDoneTest() {
        Task task = new Todo("Test Task");
        task.markAsDone();
        task.markAsUnDone();
        assertFalse(task.isDone);
    }

    /**
     * Test the getStatusIconTest method when the task is not done.
     * Verifies that the status icon is a " " when task is not done.
     */
    @Test
    public void getStatusIconTest() {
        Task task = new Todo("Test Task");
        String ans = task.getStatusIcon();
        assertFalse(task.isDone);
        assertEquals(" ", ans);
    }

    /**
     * Test the getStatusIconTest method when the task is done.
     * Verifies that the status icon is a "X" when task is done.
     */
    @Test
    public void getStatusIconDoneTest() {
        Task task = new Todo("Test Task");
        task.markAsDone();
        String ans = task.getStatusIcon();
        assertTrue(task.isDone);
        assertEquals("X", ans);
    }

}
