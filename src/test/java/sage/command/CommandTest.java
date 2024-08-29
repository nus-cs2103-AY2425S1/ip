package sage.command;

import org.junit.jupiter.api.Test;
import sage.exception.SageExceptionTest;
import sage.task.TaskList;
import sage.task.ToDo;
import sage.ui.Ui;
import sage.storage.Storage;
import sage.exception.SageException;

import static org.junit.jupiter.api.Assertions.*;
public class CommandTest {
    @Test
    public void testIsExit_defaultBehaviour() {
        // Create an anonymous subclass of command
        Command command = new Command() {
            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
                // Mock Implementation
            }
        };

        // Test that isExit returns false by default
        assertFalse(command.isExit(), "isExit should return false by default");
    }

    @Test
    public void testExecute() {
        //Create an anonymous subclass of command
        Command command = new Command() {
            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
                // Example: Add a task to the task list
                tasks.addTask(new ToDo("Example Task"));
            }
        };

        // Mock the dependencies
        TaskList mockTaskLists = new TaskList();
        Ui mockUi = new Ui();
        Storage mockStorage = new Storage("data/sage.txt");

        // Execute the command
        try {
            command.execute(mockTaskLists, mockUi, mockStorage);
            // Test that the task was added
            assertEquals(1, mockTaskLists.size(), "TaskList should have one task after execution");
        } catch (SageException e) {
            fail("Execution should not throw SageException");
        }
    }

}
