package taskType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskInitialization() {
        Task task = new Task("Test task");
        assertEquals("Test task", task.getDescription());
        assertFalse(task.getStatusIcon().equals("[X]"));
    }

    @Test
    public void testGetDescription() {
        Task task = new Task("test task");
        assertEquals("test task", task.getDescription());
    }

    @Test
    public void testGetStatusIconWhenNotDone() {
        Task task = new Task("Another task");
        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    public void testGetStatusIconWhenDone() {
        Task task = new Task("Fire off!");
        task.setDone(true);
        assertEquals("[X]", task.getStatusIcon());
    }
    @Test
    public void testSetDoneTwice() {
        Task task = new Task("Switch done status");
        task.setDone(true);
        assertEquals("[X]", task.getStatusIcon(), "Task should be marked as done.");

        task.setDone(false);
        assertEquals("[ ]", task.getStatusIcon(), "Task should be marked as not done after resetting.");
    }

    @Test
    public void testIsEqualSameTasks() {
        Task task1 = new Task("Test equality");
        Task task2 = new Task("Test equality");

        assertTrue(task1.isEqual(task2), "Tasks with the same description should be equal.");
    }

    @Test
    public void testIsEqualDifferentTasks() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");

        assertFalse(task1.isEqual(task2), "Tasks with different descriptions should not be equal.");
    }

    @Test
    public void testIsEqualWithDifferentDoneStatus() {
        Task task1 = new Task("Test status");
        Task task2 = new Task("Test status");

        task2.setDone(true);
        assertFalse(task1.isEqual(task2), "Tasks with the same description but different done status should not be equal.");
    }

    @Test
    public void testToString() {
        Task task = new Task("ToString test");
        assertEquals("[ ] ToString test", task.toString());

        task.setDone(true);
        assertEquals("[X] ToString test", task.toString(), "toString should reflect the done status.");
    }


}
