package moimoi.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {

    Command command;

    @Test
    public void testisExit() {
        this.command = new ExitCommand();
        assertTrue(this.command.isExit());
    }

}
