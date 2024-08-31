package mryapper.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testMarkAndUnmark() {
        Task testTask = new Deadline("mark and unmark test", "today");

        // marking a task as done
        testTask.markAsDone();
        assertEquals("[D][X] mark and unmark test (by: today)", testTask.toString());

        // marking a task as not done
        testTask.markAsNotDone();
        assertEquals("[D][ ] mark and unmark test (by: today)", testTask.toString());
    }

    @Test
    public void testDeadlineDataString() {
        Task testTask = new Deadline("data string test", "tomorrow ");

        // unmarked task
        assertEquals("D ||| 0 ||| data string test ||| tomorrow \n", testTask.getDataString());

        // marked task
        testTask.markAsDone();
        assertEquals("D ||| 1 ||| data string test ||| tomorrow \n", testTask.getDataString());
    }
}
