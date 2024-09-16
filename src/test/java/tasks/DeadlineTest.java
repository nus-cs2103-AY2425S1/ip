package tasks;

import pandabot.exceptions.InputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import pandabot.tasks.Deadline;
import pandabot.tasks.Task;

/**
 * Unit tests for the Deadline class.
 * This class tests the functionality of the Deadline class, including creating tasks,
 * handling invalid input, and encoding tasks to a string format.
 */
class DeadlineTest {

    /**
     * Tests the createTask method with valid input.
     * Ensures that a valid Deadline task is created and its string representation is as expected.
     */
    @Test
    void testCreateTaskValid() throws InputException {
        Task task = new Deadline("", LocalDateTime.now()).createTask("Submit assignment /by 15/10/2024 2359");
        assertNotNull(task);
        assertEquals("[D][ ] Submit assignment (by: Oct 15 2024, 11:59 pm)", task.toString());
    }

    /**
     * Tests the createTask method with invalid input (missing or incomplete description).
     * Ensures that an InputException is thrown when trying to create a task with invalid input.
     */
    @Test
    void testCreateTaskInvalid() {
        assertThrows(InputException.class, () -> new Deadline("", LocalDateTime.now()).createTask("deadline"));
    }

    /**
     * Tests the encode method.
     * Ensures that the Deadline task is correctly encoded into a string format suitable for storage.
     */
    @Test
    void testEncode() {
        LocalDateTime date = LocalDateTime.of(2024, 10, 15, 23, 59);
        Task task = new Deadline("Submit assignment", date);
        String encoded = task.encode();
        assertEquals("D | 0 | Submit assignment | 15/10/2024 2359", encoded);
    }
}
