package orion.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

class DeadlineTest {

    @Test
    void testConstructorAndGetters() {
        LocalDateTime by = LocalDateTime.of(2024, 12, 25, 18, 0);
        Deadline deadline = new Deadline(1, "Submit report", by);

        // Using getTaskID() instead of getTaskId()
        assertEquals(1, deadline.getTaskID());
        assertEquals("Submit report", deadline.getDescription());
        assertEquals(by, deadline.getBy());
    }

    @Test
    void testGetTypeIcon() {
        Deadline deadline = new Deadline(1, "Submit report", LocalDateTime.of(2024, 12, 25, 18, 0));
        assertEquals("[D]", deadline.getTypeIcon());
    }

    @Test
    void testToString() {
        LocalDateTime by = LocalDateTime.of(2024, 12, 25, 18, 0);
        Deadline deadline = new Deadline(1, "Submit report", by);
        String expected = "[D][ ] Submit report (by: 25 Dec 2024 18:00)";
        assertEquals(expected, deadline.toString());
    }
}
