package shoai;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeadlineTest {

    @Test
    public void testDeadlineConstructor() {
        Deadline deadline = new Deadline("Submit report", "2024-09-01");
        assertNotNull(deadline);
        assertEquals("Submit report", deadline.getDescription());
        assertEquals("2024-09-01", deadline.getBy());
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit report", "2024-09-01");
        String expectedString = "[D][ ] Submit report (by: 2024-09-01)";
        assertEquals(expectedString, deadline.toString());
    }
}
