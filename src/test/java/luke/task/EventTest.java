package luke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testUndoneEventCreation() {
        Event event = new Event("project meeting", "6/7/2020 2pm", "4pm", false);
        assertEquals("[E][ ] project meeting (from: 6/7/2020 2pm to: 4pm)", event.taskDescription());
        assertEquals("- | event | project meeting from 6/7/2020 2pm to 4pm\n", event.taskInSaveData());
    }

    @Test
    public void testDoneEventCreation() {
        Event event = new Event("project meeting", "6/7/2020 2pm", "4pm", true);
        assertEquals("[E][X] project meeting (from: 6/7/2020 2pm to: 4pm)", event.taskDescription());
        assertEquals("X | event | project meeting from 6/7/2020 2pm to 4pm\n", event.taskInSaveData());
    }
}
