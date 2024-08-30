package wenjieBot;

import org.junit.jupiter.api.Test;
import wenjieBot.commands.*;

import wenjieBot.exceptions.UnknownCommandException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParseMarkCommand() throws UnknownCommandException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseUnmarkCommand() throws UnknownCommandException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseDeleteCommand() throws UnknownCommandException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseByeCommand() throws UnknownCommandException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
        assertTrue(command.isExit());
    }

    @Test
    public void testParseListCommand() throws UnknownCommandException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseUnknownCommand() {
        assertThrows(UnknownCommandException.class, () -> {
            Parser.parse("unknown command");
        });
    }
}
