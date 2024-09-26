package stobberi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stobberi.components.TaskList;
import stobberi.stobberiexception.StobberiException;

// A concrete class extending Command to test the abstract class
class TestCommand extends Command {
    public TestCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    @Override
    public String execute() throws StobberiException {
        return "Command executed";
    }
}

public class CommandTest {

    private TaskList taskList;
    private Command command;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(); // Assuming TaskList has a default constructor
        command = new TestCommand(taskList, "test command");
    }

    @Test
    public void testIsExitInitiallyFalse() {
        // By default, the isExit flag should be false
        assertFalse(command.isExit(), "The command should not exit by default.");
    }

    @Test
    public void testSetExitTrue() {
        // After calling setExitTrue, isExit should return true
        command.setExitTrue();
        assertTrue(command.isExit(), "The command should exit after calling setExitTrue.");
    }

    @Test
    public void testGetTaskList() {
        // Ensure the task list passed into the command is correctly returned
        assertEquals(taskList, command.getTaskList(), "The task list should be correctly returned.");
    }

    @Test
    public void testGetRestOfCommand() {
        // Ensure the restOfCommand is correctly returned
        assertEquals("test command", command.getRestOfCommand(), "The rest of the command should be 'test command'.");
    }

    @Test
    public void testExecute() throws StobberiException {
        // Ensure the execute method works as expected
        assertEquals("Command executed", command.execute(), "The execute method should return 'Command executed'.");
    }

    @Test
    public void testCommandWithEmptyRestOfCommand() {
        // Test when the rest of the command is empty
        Command emptyCommand = new TestCommand(taskList, "");
        assertEquals("", emptyCommand.getRestOfCommand(), "The rest of the command should be an empty string.");
    }

    @Test
    public void testCommandWithNullRestOfCommand() {
        // Test when the rest of the command is null
        Command nullCommand = new TestCommand(taskList, null);
        assertNull(nullCommand.getRestOfCommand(), "The rest of the command should be null.");
    }
}
