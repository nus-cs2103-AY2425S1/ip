package lama.command;

import lama.task.Task;
import lama.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Command
 * Contains unit test case for Command class
 */
public class CommandTest {

    /**
     * Test the isExit() method.
     * Verifies that calling isExit() return false.
     */
    @Test
    public void isExitTest() {

        Task todo = new Todo("Read Book");

        Command command = new AddCommand(todo);

        assertFalse(command.isExit());
    }
}
