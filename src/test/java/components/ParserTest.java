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
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() throws LightException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() throws LightException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseListCommand() throws LightException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand() throws LightException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseTodoCommand() throws LightException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseDeadlineCommand() throws LightException {
        Command command = Parser.parse("deadline return book /by 2023-10-10");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseEventCommand() throws LightException {
        Command command = Parser.parse("event project meeting /from 2023-10-10 /to 2023-10-11");
        assertInstanceOf(AddCommand.class, command);
    }
}