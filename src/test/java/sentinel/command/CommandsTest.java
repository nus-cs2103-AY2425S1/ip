package sentinel.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandsTest {
    @Test
    public void getCommand_validCommand_success() {
        assertEquals(Commands.LIST_TASKS, Commands.getCommand("list"));
    }

    @Test
    public void getCommand_invalidCommand_nullOutput() {
        assertNull(Commands.getCommand("ohyea"));
    }
}
