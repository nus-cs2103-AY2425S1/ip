package main.java.angel;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Deadline class.
 */
class DeadlineTest {

    @Test
    void testCreateDeadlineValid() {
        // Create a Deadline task with a valid description and date/time
        LocalDateTime dueDate = LocalDateTime.of(2024, 10, 15, 23, 59);
        Deadline deadline = new Deadline("Submit assignment", dueDate);

        // Assert that the Deadline task is created correctly
        assertNotNull(deadline);
        assertEquals("[D][ ] Submit assignment (by: Oct 15 2024, 2359)", deadline.toString());
    }

    @Test
    void testToString() {
        // Test the string representation of the Deadline task
        LocalDateTime dueDate = LocalDateTime.of(2024, 10, 15, 23, 59);
        Deadline deadline = new Deadline("Complete project", dueDate);

        // Assert that the string representation matches the expected format
        assertEquals("[D][ ] Complete project (by: Oct 15 2024, 2359)", deadline.toString());
    }

    @Test
    void testToSaveFormat() {
        // Test the save format of the Deadline task
        LocalDateTime dueDate = LocalDateTime.of(2024, 10, 15, 23, 59);
        Deadline deadline = new Deadline("Submit assignment", dueDate);

        // Assert that the save format is correctly encoded
        assertEquals("D | 0 | Submit assignment | 2024/10/15 2359", deadline.toSaveFormat());
    }

    @Test
    void testNullDateHandling() {
        // Test creating a Deadline with a null due date (should throw an exception)
        assertThrows(NullPointerException.class, () -> new Deadline("Invalid task", null));
    }
}
