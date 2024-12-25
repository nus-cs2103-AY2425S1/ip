package park.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EventTest {

    @Test
    public void encode_event_success() {
        Event e = new Event("desc", "2000-12-12 1800", "2000-12-12 2000");
        assertEquals("E/[ ]/desc/Dec 12 2000, 6:00pm/Dec 12 2000, 8:00pm", e.encode());
    }

    @Test
    public void toString_event_success() {
        Event e = new Event("desc", "2000-12-12 1800", "2000-12-12 2000");
        assertEquals("[E][ ] desc (from: Dec 12 2000, 6:00pm to: Dec 12 2000, 8:00pm)", e.toString());
    }
}
