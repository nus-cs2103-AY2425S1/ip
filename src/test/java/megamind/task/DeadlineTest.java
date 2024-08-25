package megamind.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructor_success() {
        try {
            new Deadline("project meeting", "11/11/1111 1111");
        } catch (Exception e) {
            assertEquals("Text '11/11/1111 1111' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidDateTimeFormat_exceptionThrown() {
        try {
            new Deadline("project meeting", "test");
        } catch (Exception e) {
            assertEquals("Text 'test' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("project meeting", "11/11/1111 1111");
        assertEquals("[D][❌] project meeting (BY: 11 Nov 1111, 11:11:00 am)",
                deadline.toString());
    }
}
