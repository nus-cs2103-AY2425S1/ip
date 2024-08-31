package mryapper.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testMarkAndUnmark() {
        Task testTask = new Event("mark and unmark test", "3 pm", "5 pm");

        // marking a task as done
        testTask.markAsDone();
        assertEquals("[E][X] mark and unmark test (from: 3 pm | to: 5 pm)",
                testTask.toString());

        // marking a task as not done
        testTask.markAsNotDone();
        assertEquals("[E][ ] mark and unmark test (from: 3 pm | to: 5 pm)",
                testTask.toString());
    }

    @Test
    public void testDeadlineDataString() {
        Task testTask = new Event("data string test", "31 Aug", "5 Sep");

        // unmarked task
        assertEquals("E ||| 0 ||| data string test ||| 31 Aug ||| 5 Sep\n",
                testTask.getDataString());

        // marked task
        testTask.markAsDone();
        assertEquals("E ||| 1 ||| data string test ||| 31 Aug ||| 5 Sep\n",
                testTask.getDataString());
    }
}
