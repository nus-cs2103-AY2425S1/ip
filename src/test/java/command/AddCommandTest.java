package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import exception.ScheduloException;
import task.TaskList;
import task.Todo;
import util.Storage;

/**
 * Unit test for the AddCommand class.
 */
public class AddCommandTest {

    /**
     * Tests the execution of the AddCommand by adding a Todo task to the TaskList.
     *
     * @throws IOException       If an I/O error occurs during the execution of the command.
     * @throws ScheduloException If an application-specific error occurs during the execution of the command.
     */
    @Test
    public void testAddCommand() throws IOException, ScheduloException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Todo todo = new Todo("Test task");
        Command command = new AddCommand(todo);

        command.execute(taskList, storage);
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Test task", taskList.getTasks().get(0).getName());
    }
}
