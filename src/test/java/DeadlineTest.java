import Tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains tests for the Deadline class, particularly focusing on the output of the
 * toFileFormat method. It verifies that the method returns the correct string format for storing
 * deadline tasks in a file.
 */
class DeadlineTest {

    /**
     * Test to ensure that the toFileFormat method of the Deadline class correctly formats the output string.
     * This test checks whether the method properly combines the task type, completion status, description,
     * and deadline date into a formatted string suitable for file storage.
     */
    @Test
    public void toFileFormatTest() {
        // Create a new Deadline object with the description "1" and a deadline date of "2019-03-30"
        Deadline deadline = new Deadline("1", "2019-03-30");

        // Call toFileFormat method on the Deadline object and store the result
        String toFileFormat = deadline.toFileFormat();

        // Assert that the toFileFormat method returns the correct format
        assertEquals("D | 0 | 1 | 2019-03-30", toFileFormat);
    }
}
