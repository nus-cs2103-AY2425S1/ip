package JUnitTests;

import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Task.Todo;
import Naega.Ui.Ui;
import Naega.Naega;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaegaTest {

    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        Naega naega = new Naega("test.txt");

        Todo task = new Todo("Complete project");
        tasks.addTask(task);

        assertEquals(1, tasks.size());  // Check that the task list size is 1
        assertEquals("[T][ ] Complete project", tasks.getTask(0).toString());  // Check task description
    }
}