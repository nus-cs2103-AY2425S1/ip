package susan.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventStringRepresentation() {
        Event event = new Event("project meeting", "Aug 6th 2pm", "4pm");
        String expected = "[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testMarkAsDone() {
        Event event = new Event("project meeting", "Aug 6th 2pm", "4pm");
        event.markAsDone();
        String expected = "[E][X] project meeting (from: Aug 6th 2pm to: 4pm)";
        assertEquals(expected, event.toString());
    }
}
