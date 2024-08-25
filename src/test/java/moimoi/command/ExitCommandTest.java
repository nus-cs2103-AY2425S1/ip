package moimoi.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {

    @Test
    public void testisExit() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }

}
