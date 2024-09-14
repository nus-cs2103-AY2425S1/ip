package duck.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;

/**
 * Tests the ByeCommand class.
 */
public class ByeCommandTest {


    /**
     * Tests the execute method of the ByeCommand class with null arguments.
     */
    @Test
    public void execute_nullArgs_exceptionThrown() {
        Command command = new ByeCommand("test");
        assertThrows(DuckException.class, () -> {
            command.execute(null, null, null);
        });
    }

    /**
     * Tests if the command is an exit command.
     */
    @Test
    public void isExit() {
        Command command = new ByeCommand("test");
        assertTrue(command.isExit(), "The command is an exit command.");
    }
}
