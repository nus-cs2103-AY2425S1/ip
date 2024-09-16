package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import pandabot.exceptions.InputException;
import pandabot.tasks.Event;
import pandabot.tasks.Task;


/**
 * Unit tests for the Event class.
 * This class tests the functionality of the Event class, including creating tasks,
 * handling invalid input, and encoding tasks to a string format.
 */
class EventTest {

    /**
     * Tests the createTask method with valid input.
     * Ensures that a valid Event task is created and its string representation is as expected.
     */
    @Test
    void testCreateTaskValid() throws InputException {
        Task task = new Event("", LocalDateTime.now(), LocalDateTime.now())
                .createTask("Meeting /from 01/01/2024 0900 /to 01/01/2024 1700");
        assertNotNull(task);
        assertEquals("[E][ ] Meeting (from: Jan 1 2024, 9:00 am, to: Jan 1 2024, 5:00 pm)", task.toString());
    }

    /**
     * Tests the createTask method with invalid input (missing or incomplete description).
     * Ensures that an InputException is thrown when trying to create a task with invalid input.
     */
    @Test
    void testCreateTaskInvalid() {
        assertThrows(InputException.class, () -> new Event("", LocalDateTime.now(),
                LocalDateTime.now()).createTask("event"));
    }

    /**
     * Tests the encode method.
     * Ensures that the Event task is correctly encoded into a string format suitable for storage.
     */
    @Test
    void testEncode() {
        LocalDateTime from = LocalDateTime.of(2024, 1, 1, 9, 0);
        LocalDateTime to = LocalDateTime.of(2024, 1, 1, 17, 0);
        Task task = new Event("Meeting", from, to);
        String encoded = task.encode();
        assertEquals("E | 0 | Meeting | 1/1/2024 0900 | 1/1/2024 1700", encoded);
    }
}
