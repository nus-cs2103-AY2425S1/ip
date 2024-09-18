package ollie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Task} class.
 */
public class TaskTest {

    private Task task;

    /**
     * Initializes a stub of the {@link Task} class for testing.
     * This stub allows us to test the functionality of the abstract {@link Task} class.
     */
    @BeforeEach
    public void setUp() {
        task = new TaskStub("Test Task", TaskType.TODO);
    }

    /**
     * Tests the {@link Task#getStatusIcon()} method.
     * Verifies that the status icon is " " for an incomplete task and "X" for a completed task.
     */
    @Test
    public void getStatusIcon_incompleteTask() {
        assertEquals(" ", task.getStatusIcon());
    }

    /**
     * Tests the {@link Task#getStatusIcon()} method.
     * Verifies that the status icon is " " for an incomplete task and "X" for a completed task.
     */
    @Test
    public void getStatusIcon_completedTask() {
        task.markAsDone(true);
        assertEquals("X", task.getStatusIcon());
    }

    /**
     * Tests the {@link Task#saveAsString()} method.
     * Verifies that the task is saved correctly as a string.
     */
    @Test
    public void saveAsString_correctFormat() {
        String expectedString = "TODO |   | Test Task";
        assertEquals(expectedString, task.saveAsString());
    }

    /**
     * Tests the {@link Task#markAsDone(boolean)} method.
     * Verifies that the task's completion status can be marked as done.
     */
    @Test
    public void markAsDone_updatesStatusAsDone() {
        task.markAsDone(true);
        assertTrue(task.getIsDone());
    }

    /**
     * Tests the {@link Task#markAsDone(boolean)} method.
     * Verifies that the task's completion status can be marked as undone.
     */
    @Test
    public void markAsDone_updatesStatusAsUndone() {
        task.markAsDone(false);
        assertFalse(task.getIsDone());
    }

    /**
     * Tests the {@link Task#toString()} method.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void toString_correctFormat() {
        String expectedString = "[ ] [TODO] Test Task";
        assertEquals(expectedString, task.toString());
    }

    /**
     * Stub implementation of the {@link Task} class for testing purposes.
     * This stub does not need to implement the abstract methods.
     */
    private static class TaskStub extends Task {
        public TaskStub(String description, TaskType taskType) {
            super(description, taskType);
        }
    }
}
