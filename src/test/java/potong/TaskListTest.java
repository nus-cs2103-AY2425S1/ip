package potong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import potong.task.Task;


public class TaskListTest {

    @Test
    public void testAdd() throws Exception {
        assertEquals("Got it. I've added this task:\n [ ][ ] read book\nNow you have 1 tasks in the list.",
                new TaskList().add(new Task("read book")));
    }

    @Test
    public void testMark() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Task("read book"));
        assertEquals("Nice! I've marked this task as done:\n [X][ ] read book", tasks.mark(1));
    }

    @Test
    public void testMarkException() {
        try {
            assertEquals("", new TaskList().mark(1));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!! meow have a wrong input!\n"
                    + "Your index 1 is out of bounds!", e.getMessage());
        }
    }
    @Test
    public void testDelete() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Task("finish project"));
        assertEquals("Noted. I've removed this task:\n "
                + "[ ][ ] finish project\nNow you have 0 tasks in the list.", tasks.delete(1));
    }
    @Test
    public void testTag() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Task("help people"));
        assertEquals("OK, I've tagged this task as super\n "
                + "[ ][#super] help people", tasks.tag("super", 1));
    }
}
