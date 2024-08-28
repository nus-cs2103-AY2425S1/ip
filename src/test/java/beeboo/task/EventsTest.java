package beeboo.task;

import beeboo.exception.BeeBooExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void createEventTest() throws BeeBooExceptions {
        String test = "meeting /from 2024-02-28 0600 /to 0800";
        String expected = "[E][ ] meeting(from: Feb 28 2024 at 06:00 am to: Feb 28 2024 at 08:00 am)";

        assertEquals(expected,Events.CreateEvent(test).toString());
    }
}
