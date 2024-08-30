package yapmeister.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testValidDate() {
        Event event = new Event("jump", "tomorrow", "2019-02-18");
        assertEquals("[E][ ] jump (from: tomorrow to: 2019-W08-1)", event.toString());
    }
}
