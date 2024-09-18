package bopes.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ToDo class, which represents a simple task without a specific deadline.
 */
public class ToDoTest {

    /**
     * Tests the constructor and getter methods of the ToDo class.
     * It verifies the description and completion status of the task.
     */
    @Test
    public void testConstructorAndGetters() {
        // Test when the task is not done
        ToDo todo = new ToDo("Test task", false);
        assertEquals("Test task", todo.description);
        assertFalse(todo.isDone);

        // Test when the task is done
        ToDo todoDone = new ToDo("Completed task", true);
        assertEquals("Completed task", todoDone.description);
        assertTrue(todoDone.isDone);
    }

    /**
     * Tests the toString method of the ToDo class.
     * It verifies the string representation of the task based on its completion status.
     */
    @Test
    public void testToString() {
        // Test when the task is not done
        ToDo todo = new ToDo("Test task", false);
        assertEquals("[T][ ] Test task", todo.toString());

        // Test when the task is done
        ToDo todoDone = new ToDo("Completed task", true);
        assertEquals("[T][X] Completed task", todoDone.toString());
    }

    /**
     * Tests the toFileFormat method of the ToDo class.
     * It verifies the file-friendly string representation of the task based on its completion status.
     */
    @Test
    public void testToFileFormat() {
        // Test when the task is not done
        ToDo todo = new ToDo("Test task", false);
        assertEquals("T | 0 | Test task", todo.toFileFormat());

        // Test when the task is done
        ToDo todoDone = new ToDo("Completed task", true);
        assertEquals("T | 1 | Completed task", todoDone.toFileFormat());
    }
}
