package barcus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Todo;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

public class TagCommandTest {
    @Test
    public void testExecute() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("./data/savedTasks.txt");
            tasks.addTask(new Todo("task1"));
            TagCommand c = new TagCommand(1, "tag1");
            c.execute(tasks, ui, storage);
            assertEquals("[T][ ] task1 [#tag1]", tasks.getTaskString(0));
        } catch (BarcusException e) {
            fail();
        }
    }

    @Test
    public void testWrongExecuteWrongPos() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            Storage storage = new Storage("./data/savedTasks.txt");
            tasks.addTask(new Todo("task1"));
            TagCommand c = new TagCommand(7, "tag1");
            c.execute(tasks, ui, storage);
            fail();
        } catch (BarcusException e) {
            assertEquals("please choose a number between 1 and 1", e.getMessage());
        }
    }
}
