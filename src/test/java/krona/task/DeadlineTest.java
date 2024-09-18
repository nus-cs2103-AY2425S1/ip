package krona.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit assignment", "30/8/2024 2359");
        String expectedOutput = "[D][ ] Submit assignment (by: Aug 30 2024, 11:59 pm)";
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("read book", "2024-08-30 2359");
        });
    }
}
