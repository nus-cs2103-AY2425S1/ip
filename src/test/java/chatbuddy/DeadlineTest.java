package chatbuddy;

import chatbuddy.task.Deadline;
import chatbuddy.exception.ChatBuddyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() throws ChatBuddyException {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-10");
        assertEquals("[D][ ] Submit assignment (by: Sept 10 2024)", deadline.toString());
    }

    @Test
    public void testMarkAsDone() throws ChatBuddyException {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-10");
        deadline.markAsDone();
        assertEquals("[D][X] Submit assignment (by: Sept 10 2024)", deadline.toString());
    }

    @Test
    public void testUnmarkAsDone() throws ChatBuddyException {
        Deadline deadline = new Deadline("Submit assignment", "2024-09-10");
        deadline.markAsDone();
        deadline.unmarkAsDone();
        assertEquals("[D][ ] Submit assignment (by: Sept 10 2024)", deadline.toString());
    }
}
