package yapyap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadline_validDateString_deadlineCreatedSuccessfully() throws YapperBotException {
        Deadline deadline = new Deadline("finish ip", "2024-12-02");
        assertEquals("finish ip", deadline.getDescription());
        assertEquals("[D][ ] finish ip (by: Dec 02 2024)", deadline.toString());
    }

    @Test
    public void deadline_invalidDateString_throwsException() {
        Exception exception = assertThrows(YapperBotException.class, () -> {
            new Deadline("submit assignment", "invalid-date");
        });
        assertEquals(exception.getMessage(), "Invalid date format! Please use either 'd/M/yyyy HHmm' "
                + "or 'yyyy-MM-dd'.");
    }

    @Test
    public void mark_deadlineMarked_deadlineMarkedSuccessfully() throws YapperBotException {
        Deadline deadline = new Deadline("eat dinner", "2024-12-02");
        deadline.mark();
        assertTrue(deadline.getIsDone());
        assertEquals("[D][X] eat dinner (by: Dec 02 2024)", deadline.toString());
    }

    @Test
    public void unmark_deadlineUnmarked_deadlineUnmarkedSuccessfully() throws YapperBotException {
        Deadline deadline = new Deadline("eat dinner", "2024-12-02", true);
        assertEquals("[D][X] eat dinner (by: Dec 02 2024)", deadline.toString());
        deadline.unmark();
        assertFalse(deadline.isDone);
        assertEquals("[D][ ] eat dinner (by: Dec 02 2024)", deadline.toString());
    }
}


















