package main.java.angel;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the Deadline class.
 */
class DeadlineTest {

    /**
     * Tests the creation of a Deadline instance with a valid description and due date/time.
     */
    @Test
    void testCreateDeadlineValid() {
        // Create a Deadline task with a valid description and date/time
        LocalDateTime dueDate = LocalDateTime.of(2024, 10, 15, 23, 59);
        Deadline deadline = new Deadline("Submit assignment", dueDate);

        // Assert that the Deadline task is created correctly
        assertNotNull(deadline, "The deadline should not be null");
        assertEquals("[D][ ] Submit assignment (by: Oct 15 2024, 2359)", deadline.toString(),
                "The string representation of the deadline is incorrect");
    }

    /**
     * Tests the string representation of a Deadline instance.
     */
    @Test
    void testToString() {
        // Test the string representation of the Deadline task
        LocalDateTime dueDate = LocalDateTime.of(2024, 10, 15, 23, 59);
        Deadline deadline = new Deadline("Complete project", dueDate);

        // Assert that the string representation matches the expected format
        assertEquals("[D][ ] Complete project (by: Oct 15 2024, 2359)", deadline.toString(),
                "The string representation of the deadline is incorrect");
    }

    /**
     * Tests the save format of a Deadline instance.
     */
    @Test
    void testToSaveFormat() {
        // Test the save format of the Deadline task
        LocalDateTime dueDate = LocalDateTime.of(2024, 10, 15, 23, 59);
        Deadline deadline = new Deadline("Submit assignment", dueDate);

        // Assert that the save format is correctly encoded
        assertEquals("D | 0 | Submit assignment | 2024/10/15 2359", deadline.toSaveFormat(),
                "The save format of the deadline is incorrect");
    }

    /**
     * Tests handling of a Deadline instance with a null due date.
     */
    @Test
    void testNullDateHandling() {
        // Test creating a Deadline with a null due date (should throw an exception)
        assertThrows(NullPointerException.class, () -> new Deadline("Invalid task", null),
                "Creating a deadline with a null due date should throw a NullPointerException");
    }
}
