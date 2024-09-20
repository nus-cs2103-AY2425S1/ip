package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Task class.
 */
public class TaskTest {

    /**
     * Checks if the Task constructor works correctly.
     */
    public void TaskConstructorTest() {
        Task task = new Task("description");

        assertEquals(task.getDescription(), "description");
        assertEquals(task.getStatusIcon(), " ");
    }

    /**
     * Checks if the markAsDone method works correctly.
     */
    public void markAsDoneTest() {
        Task task = new Task("description");
        
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "X");
    }
}
