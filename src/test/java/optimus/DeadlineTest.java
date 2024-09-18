package optimus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Asked ChatGPT how to test for Exceptions
public class DeadlineTest {
    @Test
    public void testToString() throws OptimusException {
        // Create a new Deadline instance with a description and a date
        Deadline deadline = new Deadline("finish project", "2024-10-15");

        // Test that the toString method outputs the correct string
        assertEquals("[D][ ] finish project (by: Oct 15 2024)", deadline.toString());

        // Test for invalid date format
        Exception exception = assertThrows(OptimusException.class, () -> {
            new Deadline("submit assignment", "Dec 31 2024");
        });

        // Verify that the correct error message is returned
        assertEquals("Invalid date format for deadline. Please use yyyy-mm-dd.", exception.getMessage());
    }
}
