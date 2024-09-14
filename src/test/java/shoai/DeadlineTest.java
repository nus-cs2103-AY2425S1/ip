package shoai;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;

public class DeadlineTest {

    @Test
    public void testDeadlineConstructor() {
        // Create a Deadline instance with date and time
        LocalDateTime deadlineDateTime = LocalDateTime.parse("2024-09-01T10:00");
        Deadline deadline = new Deadline("Submit report", deadlineDateTime);

        // Validate the constructor
        assertNotNull(deadline);
        assertEquals("Submit report", deadline.getDescription());
        assertEquals(deadlineDateTime, deadline.getBy());
    }

    @Test
    public void testToString() {
        // Create a Deadline instance with date and time
        LocalDateTime deadlineDateTime = LocalDateTime.parse("2024-09-01T10:00");
        Deadline deadline = new Deadline("Submit report", deadlineDateTime);

        // Validate the toString method output
        String expectedString = "[D][ ] Submit report (by: 2024-09-01 10:00)";
        assertEquals(expectedString, deadline.toString());
    }
}
