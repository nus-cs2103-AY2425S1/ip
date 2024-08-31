package Majima.task;

import Majima.MajimaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void EventTest() {
        String description = "Michael's Birthday Party";
        String from = "Sunday, 12pm";
        String to = "Sunday, 6pm";

        Event task = null;

        task = new Event(description, from, to);

        assertEquals(description, task.getDescription());
        assertEquals("[E][ ] Michael's Birthday Party (from: Sunday, 12pm to: Sunday, 6pm)", task.toString());

    }
}
