package tasks;

import pandabot.exceptions.InputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import pandabot.tasks.Task;
import pandabot.tasks.ToDo;

/**
 * Unit tests for the ToDo class.
 * This class tests the functionality of the ToDo class, including creating tasks,
 * handling invalid input, and encoding tasks to a string format.
 */
class ToDoTest {

    /**
     * Tests the createTask method with valid input.
     * Ensures that a valid ToDo task is created and its string representation is as expected.
     */
    @Test
    void testCreateTaskValid() throws InputException {
        Task task = new ToDo("Test task").createTask("Test task");
        assertNotNull(task);
        assertEquals("[T][ ] Test task", task.toString());
    }

    /**
     * Tests the createTask method with invalid input (missing description).
     * Ensures that an InputException is thrown when trying to create a task with invalid input.
     */
    @Test
    void testCreateTaskInvalid() {
        assertThrows(InputException.class, () -> new ToDo("").createTask("todo"));
    }

    /**
     * Tests the encode method.
     * Ensures that the ToDo task is correctly encoded into a string format suitable for storage.
     */
    @Test
    void testEncode() {
        Task task = new ToDo("Test task");
        String encoded = task.encode();
        assertEquals("T | 0 | Test task", encoded);
    }
}
