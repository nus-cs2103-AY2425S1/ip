package elon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import elon.command.Command;
import elon.command.ExitCommand;
import elon.command.ListTaskCommand;

class ParserTest {
    @Test
    void testParseListCommand() throws ElonException {
        Command command = Parser.parse(new String[]{"list"});
        assertTrue(command instanceof ListTaskCommand);
    }

    @Test
    void testParseExitCommand() throws ElonException {
        Command command = Parser.parse(new String[]{"bye"});
        assertTrue(command instanceof ExitCommand);
    }
}

