package rose.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testStringPrinting() {
        Event event = new Event("Hackathon", LocalDate.parse("2024-12-01"),
                LocalDate.parse("2024-12-04"));
        assertEquals("[E][ ] Hackathon (from: Dec 01 2024 to: Dec 04 2024)", event.toString());
        assertEquals("E, ,Hackathon,2024-12-01,2024-12-04", event.commaString());
    }

    @Test
    public void testMarkUnmark() {
        Event event = new Event("Hackathon", LocalDate.parse("2024-12-01"),
                LocalDate.parse("2024-12-04"));
        assertFalse(event.isDone);

        event.mark();
        assertTrue(event.isDone);

        event.unmark();
        assertFalse(event.isDone);
    }
}
