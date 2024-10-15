package chatkaki.tasks;

import chatkaki.tasks.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void fileFormat_validEvent_correctFormat() {
        Event event = new Event(false, "project meeting", LocalDateTime.of(2019, 12, 2, 18, 0), LocalDateTime.of(2019, 12, 2, 20, 0));
        assertEquals("EVENT,false,project meeting,2/12/2019 1800,2/12/2019 2000", event.fileFormat());
    }

    @Test
    public void toString_validEvent_correctString() {
        Event event = new Event(false, "project meeting", LocalDateTime.of(2019, 12, 2, 18, 0), LocalDateTime.of(2019, 12, 2, 20, 0));
        assertEquals("[E][ ] project meeting (from: 2/12/2019 1800 to: 2/12/2019 2000)", event.toString());
    }
}