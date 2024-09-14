import Tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the Todo class in the task management application.
 * Focuses on verifying the correct behavior of the toFileFormat method,
 * ensuring it produces a string that accurately represents the state of a Todo task for file storage.
 */
class TodoTest {

    /**
     * Tests the toFileFormat method of the Todo class to ensure it returns a correctly formatted string.
     * This test verifies that the method outputs the string in the format 'T | 0 | description',
     * where 'T' indicates a Todo task, '0' indicates the task is not done, and 'description' is the task's description.
     */
    @Test
    public void toFileFormatTest() {
        // Create a new Todo object with the description "1"
        Todo todo = new Todo("1");

        // Call toFileFormat method and capture the output
        String toFileFormat = todo.toFileFormat();

        // Assert that the output matches the expected format
        assertEquals("T | 0 | 1", toFileFormat, "The toFileFormat method should return the correct string representation.");
    }
}
