package command;

import exception.ScheduloException;
import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;
import util.Storage;
import util.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the AddCommand class.
 */
public class AddCommandTest {

    /**
     * Tests the execution of the AddCommand by adding a Todo task to the TaskList.
     *
     * @throws IOException        If an I/O error occurs during the execution of the command.
     * @throws ScheduloException  If an application-specific error occurs during the execution of the command.
     */
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