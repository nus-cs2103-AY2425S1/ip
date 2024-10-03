package duck.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;

/**
 * Tests the DeadlineCommand class.
 */
public class DeadlineCommandTest {

    /**
     * Tests the execute method of the DeadlineCommand class with null arguments.
     */
    @Test
    public void execute_nullArgs_exceptionThrown() {
        Command command = new DeadlineCommand("test");
        assertThrows(DuckException.class, () -> {
            command.execute(null, null, null);
        });
    }

    /**
     * Tests if the command is an exit command.
     */
    @Test
    public void exit() {
        Command command = new DeadlineCommand("test");
        assertFalse(command.isExit(), "The command is not exit command.");
    }
}
