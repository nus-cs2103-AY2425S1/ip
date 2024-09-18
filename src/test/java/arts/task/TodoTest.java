package arts.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the test class for the Todo class.
 * This class contains unit tests for various methods in the Todo class.
 */
public class TodoTest {

    private Todo todo;

    /**
     * Sets up the test environment before each test.
     * Initializes the 'todo' instance with a sample task description.
     */
    @BeforeEach
    public void setUp() {
        todo = new Todo("Buy groceries");
    }

    /**
     * Tests the toString() method of the Todo class.
     * Verifies that the string representation of the todo matches the expected format.
     */
    @Test
    public void testToString() {
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todo.toString(), "The string representation of the todo is incorrect.");
    }

    /**
     * Tests the toFileFormat() method of the Todo class.
     * Verifies that the file format of the todo matches the expected format.
     */
    @Test
    public void testToFileFormat() {
        String expected = "T | 0 | Buy groceries";
        assertEquals(expected, todo.toFileFormat(), "The file format of the todo is incorrect.");
    }

    /**
     * Tests the markAsDone() method of the Todo class.
     * Ensures that the todo is marked as done and its string representation is updated accordingly.
     */
    @Test
    public void testMarkAsDone() {
        todo.markAsDone();
        String expected = "[T][X] Buy groceries";
        assertEquals(expected, todo.toString(), "The todo should be marked as done.");
    }
}
