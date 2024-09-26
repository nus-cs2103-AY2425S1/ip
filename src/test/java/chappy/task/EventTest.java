import chappy.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void of_success() {
        Event testEvent = Event.of("event test /from 23-09-2024 /to 25-10-2024");
        assertTrue(testEvent instanceof Event);
        assertEquals("[E][ ] test (from: Sep 23 2024 to: Oct 25 2024)", testEvent.toString());
        Event testEvent2 = Event.of("eVenT test /from 23-09-2024 /to 25-10-2024");
        assertTrue(testEvent2 instanceof Event);
        assertEquals("[E][ ] test (from: Sep 23 2024 to: Oct 25 2024)", testEvent2.toString());
        Event testEvent3 = Event.of("event test /from 23-9-24 /to 25-10-2024");
        assertTrue(testEvent3 instanceof Event);
        assertEquals("[E][ ] test (from: 23-9-24 to: 25-10-2024)", testEvent3.toString());
    }

    @Test
    public void of_fail() {
        Event testEvent = Event.of("event test");
        assertTrue(testEvent == null);
        Event testEvent2 = Event.of("event /from Mon 2pm /to ");
        assertTrue(testEvent2 == null);
        Event testEvent3 = Event.of("event /from /to ");
        assertTrue(testEvent3 == null);
        Event testEvent4 = Event.of("event");
        assertTrue(testEvent4 == null);
        Event testEvent5 = Event.of("");
        assertTrue(testEvent5 == null);
    }
}
