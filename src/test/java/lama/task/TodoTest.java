package lama.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for to-do class.
 * Contains unit test case for to-do class.
 */
public class TodoTest {

    /**
     * Test the constructor of to-do class.
     * Verifies that a to-do object is correctly initialized and
     * its toString method returns the expected format.
     */
    @Test
    public void constructorTest() {
        String description = "Return Book";
        Task todo = new Todo(description);

        String output = "[T][ ] Return Book";

        assertEquals(output, todo.toString());
    }

    /**
     * Test the toFile method.
     * Verifies that the toFile method returns the correct string representation
     * of the to-do object for file storage.
     */
    @Test
    public void toFileTest() {
        String description = "Return Book";
        Task todo = new Todo(description);

        String output = "T | 0 | Return Book";

        assertEquals(output, todo.toFileFormat());
    }
}
