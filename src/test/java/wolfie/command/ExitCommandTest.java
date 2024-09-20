package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExitCommandTest {
    @Test
    void testIsExit() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}
