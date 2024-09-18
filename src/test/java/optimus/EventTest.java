package optimus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Asked ChatGPT how to test for Exceptions
public class EventTest {

    @Test
    public void testToFileFormat() throws OptimusException {
        // Create a new Event instance with a description, start date, and end date
        Event event = new Event("project meeting", "2024-10-15", "2024-10-16");

        // Test that the toFileFormat method outputs the correct string
        assertEquals("E | 0 | project meeting | 2024-10-15 | 2024-10-16", event.toFileFormat());

        // Test case with invalid start date
        Exception exception1 = assertThrows(OptimusException.class, () -> {
            new Event("invalid event", "invalid-date", "2024-10-16");
        });
        assertEquals("Invalid date format for Event. Please use yyyy-mm-dd.", exception1.getMessage());

        // Test case with invalid end date
        Exception exception2 = assertThrows(OptimusException.class, () -> {
            new Event("invalid event", "2024-10-15", "invalid-date");
        });
        assertEquals("Invalid date format for Event. Please use yyyy-mm-dd.", exception2.getMessage());
    }
}
