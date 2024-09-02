package potong;

import org.junit.jupiter.api.Test;
import potong.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void testAdd() throws Exception {
        assertEquals("Got it. I've added this task:\n [ ] read book\nNow you have 1 tasks in the list.",
                new TaskList().add(new Task("read book")));
    }

    @Test
    public void testMark() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Task("read book"));
        assertEquals("Nice! I've marked this task as done:\n [X] read book", tasks.mark(1));
    }

    @Test
    public void testMarkException() {
        try {
            assertEquals("", new TaskList().mark(1));
            fail();
        } catch (Exception e) {
            assertEquals("We cannot mark a task thats not there!", e.getMessage());
        }
    }
}
