package wenjiebot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import wenjiebot.commands.ByeCommand;
import wenjiebot.commands.Command;
import wenjiebot.commands.DeleteCommand;
import wenjiebot.commands.ListCommand;
import wenjiebot.commands.MarkCommand;
import wenjiebot.commands.UnmarkCommand;
import wenjiebot.exceptions.NoFollowUpException;
import wenjiebot.exceptions.UnknownCommandException;

public class ParserTest {
    @Test
    public void testParseMarkCommand() throws UnknownCommandException, NoFollowUpException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseUnmarkCommand() throws UnknownCommandException, NoFollowUpException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseDeleteCommand() throws UnknownCommandException, NoFollowUpException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseByeCommand() throws UnknownCommandException, NoFollowUpException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
        assertTrue(command.isExit());
    }

    @Test
    public void testParseListCommand() throws UnknownCommandException, NoFollowUpException {
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
