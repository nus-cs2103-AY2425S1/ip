package duch.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("rag day", "12/02/2002 1800", "13/02/2002 1800", false);
        String expected = "[E][ ] rag day (from: Feb-12-2002 1800 to: Feb-13-2002 1800)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToStore() {
        Event event = new Event("rag day", "12/02/2002 1800", "13/02/2002 1800", false);
        String expected = "E|0|rag day|12/02/2002 1800|13/02/2002 1800";
        assertEquals(expected, event.toStore());
    }
}
