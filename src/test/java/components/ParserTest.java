package components;

import static org.junit.jupiter.api.Assertions.*;

import components.Parser;
import org.junit.jupiter.api.Test;
import command.*;
import exceptions.LightException;

public class ParserTest {

    @Test
    public void testParseByeCommand() throws LightException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseMarkCommand() throws LightException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseUnmarkCommand() throws LightException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseListCommand() throws LightException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseDeleteCommand() throws LightException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseTodoCommand() throws LightException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws LightException {
        Command command = Parser.parse("deadline return book /by 2023-10-10");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testParseEventCommand() throws LightException {
        Command command = Parser.parse("event project meeting /from 2023-10-10 /to 2023-10-11");
        assertTrue(command instanceof AddCommand);
    }
}