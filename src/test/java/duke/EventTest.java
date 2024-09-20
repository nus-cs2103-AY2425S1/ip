package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void getWriteFormatTest() {
        Event event = new Event("complete 2103 week 4 tasks", "Mon 4pm", "Tues 6pm");

        //test case 1
        event.getWriteFormat();
        assertEquals("E , 0 , complete 2103 week 4 tasks , Mon 4pm-Tues 6pm", event.getWriteFormat());

        //test case 2
        event.setDone(true);
        assertEquals("E , 1 , complete 2103 week 4 tasks , Mon 4pm-Tues 6pm", event.getWriteFormat());
    }

    @Test
    public void toStringTest() {
        //test case 1
        Event event = new Event("complete 2103 week 4 tasks", "Mon 4pm", "Tues 6pm");
        assertEquals(event.toString(), "[E][ ] complete 2103 week 4 tasks (from: Mon 4pm to: Tues 6pm)");
        event.setDone(true);
        assertEquals(event.toString(), "[E][X] complete 2103 week 4 tasks (from: Mon 4pm to: Tues 6pm)");
    }
}
