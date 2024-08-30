package kobe.util;

import kobe.command.Command;
import kobe.command.AddCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test //Verifies that a valid todo command is parsed correctly into an AddCommand.
    public void testParseAddCommand() throws Exception {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddCommand, "Command should be of type AddCommand.");
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(Exception.class, () -> {
            Parser.parse("invalid command");
        }, "Parsing an invalid command should throw a KobeException.");
    }
}
