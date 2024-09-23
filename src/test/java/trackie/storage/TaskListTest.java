package trackie.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import trackie.tasks.Deadline;
import trackie.tasks.Task;
import trackie.tasks.Todo;

public class TaskListTest {
    @Test
    public void testAddingCommands() {
        TaskList tl = new TaskList();
        tl.addTask(new Todo("hi lmao"));
        tl.addTask(new Deadline("testing", "20200101 0000"));
        assertEquals(tl.size(), 2);
    }

    @Test
    public void testMarkingTasks() {
        TaskList tl = new TaskList();
        Task target = new Todo("hi lmao");
        tl.addTask(target);
        tl.markTask(0);
        assertEquals("X", target.getStatusIcon());
    }

    @Test
    public void testUnmarkingTasks() {
        TaskList tl = new TaskList();
        //Add a task with status initially set to completed
        Task target = new Todo("hi lmao", 1);
        tl.addTask(target);
        tl.unmarkTask(0);
        assertEquals(" ", target.getStatusIcon());
    }
}
