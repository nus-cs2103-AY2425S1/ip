package kobe.util;

import kobe.command.Command;
import kobe.command.AddCommand;
import kobe.command.MarkCommand;
import kobe.KobeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test // Verifies that a valid todo command is parsed correctly into an AddCommand.
    public void testParseAddCommand() throws KobeException {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddCommand, "Command should be of type AddCommand.");
    }

    @Test // Verifies that a valid mark command is parsed correctly into a MarkCommand.
    public void testParseMarkCommand() throws KobeException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand, "Command should be of type MarkCommand.");
    }

    @Test // Verifies that an invalid command throws a KobeException.
    public void testParseInvalidCommand() {
        assertThrows(KobeException.class, () -> {
            Parser.parse("invalid command");
        }, "Parsing an invalid command should throw a KobeException.");
    }

    @Test // Verifies that an empty command throws a KobeException.
    public void testParseEmptyCommand() {
        assertThrows(KobeException.class, () -> {
            Parser.parse("");
        }, "Parsing an empty command should throw a KobeException.");
    }
}
