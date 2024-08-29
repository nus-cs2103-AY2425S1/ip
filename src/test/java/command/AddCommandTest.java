package command;

import exception.ScheduloException;
import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;
import util.Storage;
import util.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    public void testAddCommand() throws IOException, ScheduloException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");
        Todo todo = new Todo("Test task");
        Command command = new AddCommand(todo);

        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Test task", taskList.getTasks().get(0).getName());
    }
}

