package patrick.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testCreateValidDeadline() {
        Deadline deadline = new Deadline("Submit report", "2023-12-31 2359");
        assertNotNull(deadline);
        assertEquals("Submit report", deadline.getDescription());
    }

    @Test
    public void testToStringFormat() {
        Deadline deadline = new Deadline("Submit report", "2023-12-31 2359");
        assertEquals("D | O | Submit report | Dec 31 2023 2359", deadline.toString());
    }
}
