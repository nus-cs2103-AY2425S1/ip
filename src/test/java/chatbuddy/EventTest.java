package chatbuddy;

import chatbuddy.task.Event;
import chatbuddy.exception.ChatBuddyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventCreation() throws ChatBuddyException {
        Event event = new Event("Project meeting", "2024-09-10", "2024-09-11");
        assertEquals("[E][ ] Project meeting (from: Sept 10 2024 to: Sept 11 2024)", event.toString());
    }

    @Test
    public void testMarkAsDone() throws ChatBuddyException {
        Event event = new Event("Project meeting", "2024-09-10", "2024-09-11");
        event.markAsDone();
        assertEquals("[E][X] Project meeting (from: Sept 10 2024 to: Sept 11 2024)", event.toString());
    }

    @Test
    public void testUnmarkAsDone() throws ChatBuddyException {
        Event event = new Event("Project meeting", "2024-09-10", "2024-09-11");
        event.markAsDone();
        event.unmarkAsDone();
        assertEquals("[E][ ] Project meeting (from: Sept 10 2024 to: Sept 11 2024)", event.toString());
    }
}
