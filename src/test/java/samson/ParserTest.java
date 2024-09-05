package samson;

import org.junit.jupiter.api.Test;
import samson.command.Command;
import samson.command.ExitCommand;
import samson.command.ListCommand;
import samson.command.MarkCommand;
import samson.command.UnmarkCommand;
import samson.command.DeleteCommand;
import samson.command.AddCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testParseExitCommand() throws SamException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseListCommand() throws SamException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseMarkCommand_valid() throws SamException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseMarkCommand_missingIndex() {
        assertThrows(SamException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void testParseMarkCommand_invalidIndex() {
        assertThrows(SamException.class, () ->Parser.parse("mark invalid"));
    }

    @Test
    public void testParseUnmarkCommand_valid() throws SamException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand_missingIndex() throws SamException {
        assertThrows(SamException.class, () ->Parser.parse("unmark"));
    }

    @Test
    public void testParseUnmarkCommand_invalidIndex() throws SamException {
        assertThrows(SamException.class, () ->Parser.parse("unmark invalid"));
    }

    @Test
    public void testParseDeleteCommand_valid() throws SamException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand_missingIndex() throws SamException {
        assertThrows(SamException.class, () ->Parser.parse("delete"));

    }

    @Test
    public void testParseDeleteCommand_invalidIndex() {
        assertThrows(SamException.class, () ->Parser.parse("delete invalid"));
    }

    @Test
    public void testParseToDoCommand_valid() throws SamException {
        Command command = Parser.parse("todo Write tests");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseToDoCommand_missingDescription() throws SamException {
        assertThrows(SamException.class, () ->Parser.parse("todo"));
    }

    @Test
    public void testParseDeadlineCommand_missingDeadline() throws SamException {
        assertThrows(SamException.class, () ->Parser.parse("deadline Submit assignment"));
    }
}

