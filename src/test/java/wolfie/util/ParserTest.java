package wolfie.util;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import wolfie.command.Command;
import wolfie.command.ExitCommand;
import wolfie.exception.WolfieException;

class ParserTest {

    @Test
    void testParse_validCommand() throws WolfieException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command); // ExitCommand is returned
    }

    @Test
    void testParse_invalidCommand() {
        assertThrows(WolfieException.class, () -> Parser.parse("invalidCommand")); // WolfieException is thrown
    }
}
