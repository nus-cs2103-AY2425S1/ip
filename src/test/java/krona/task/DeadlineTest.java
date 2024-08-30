package krona.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit assignment", "30/8/2024 2359");
        String expectedOutput = "[D][ ] Submit assignment (by: Aug 30 2024, 11:59 PM)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("Submit assignment", "30/8/2024 2359");
        assertEquals("Submit assignment", deadline.getDescription());
        assertEquals("30/8/2024 2359", deadline.getDateTime());
    }

    @Test
    public void testInvalidDateFormat() {
        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Submit assignment", "2024-08-30 2359"); // Incorrect format
        });
        assertNotNull(exception.getMessage());
    }
}