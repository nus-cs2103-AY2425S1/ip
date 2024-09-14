package duck.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;



/**
 * Tests the Command class.
 */
public class CommandTest {

    /**
     * Tests the execute method of the Command class with null arguments.
     */
    @Test
    public void testExecute_nullArgs_exceptionThrown() {
        assertThrows(DuckException.class, () -> {
            Command command = new Command("test") {
                @Override
                public boolean isExit() {
                    return false;
                }
            };
            command.execute(null, null, null);
        });
    }
}

