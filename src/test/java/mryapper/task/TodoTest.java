package mryapper.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testMarkAndUnmark() {
        Task testTask = new Todo("mark and unmark test");

        // marking a task as done
        testTask.markAsDone();
        assertEquals("[T][X] mark and unmark test", testTask.toString());

        // marking a task as not done
        testTask.markAsNotDone();
        assertEquals("[T][ ] mark and unmark test", testTask.toString());
    }

    @Test
    public void testTodoDataString() {
        Task testTask = new Todo("data string test");

        // unmarked task
        assertEquals("T ||| 0 ||| data string test\n", testTask.getDataString());

        // marked task
        testTask.markAsDone();
        assertEquals("T ||| 1 ||| data string test\n", testTask.getDataString());
    }
}
