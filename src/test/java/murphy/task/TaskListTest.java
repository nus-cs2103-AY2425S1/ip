package murphy.task;

import murphy.MurphyException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void deleteItem_success() throws MurphyException {
        TaskList tasks = new TaskList();
        Task task = new Todo("test");
        tasks.addItem(task);
        assertEquals(String.format("""
                        Got it. I've deleted this task:
                        %s
                        Now you have 0 task(s) in the list.""", task),
                tasks.deleteItem(1));
        assertEquals(new TaskList().toString(), tasks.toString());
    }

    @Test
    public void deleteItem_indexOutOfBounds_ExceptionThrown() throws MurphyException {
        TaskList tasks = new TaskList();
        tasks.addItem(new Todo("test"));
        try {
            tasks.deleteItem(2);
            fail();
        } catch (MurphyException e) {
            assertEquals("The task number you chose is out of the range of tasks!",
                    e.getMessage());
        }
    }
}