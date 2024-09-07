package demurebot.ui;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import demurebot.DemureBotException;
import demurebot.command.Command;
import demurebot.command.DeadlineCommand;
import demurebot.command.DeleteCommand;
import demurebot.command.EndCommand;
import demurebot.command.EventCommand;
import demurebot.command.InvalidCommand;
import demurebot.command.ListCommand;
import demurebot.command.MarkCommand;
import demurebot.command.TodoCommand;
import demurebot.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void testParseByeCommand() throws DemureBotException {
        Command command = Parser.parse("bye");
        assertInstanceOf(EndCommand.class, command);
    }

    @Test
    public void testParseListCommand() throws DemureBotException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() throws DemureBotException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() throws DemureBotException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand() throws DemureBotException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseTodoCommand() throws DemureBotException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void testParseDeadlineCommand() throws DemureBotException {
        Command command = Parser.parse("deadline submit report /by 2023-10-10 2359");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseEventCommand() throws DemureBotException {
        Command command = Parser.parse(
                "event project meeting /from 2023-10-10 1400 /to 2023-10-10 1600"
        );
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() throws DemureBotException {
        Command command = Parser.parse("invalid command");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void testParseTodoCommandWithoutDescription() {
        assertThrows(DemureBotException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void testParseDeadlineCommandWithoutDescription() {
        assertThrows(DemureBotException.class, () -> Parser.parse("deadline /by 2023-10-10 2359"));
    }

    @Test
    public void testParseEventCommandWithoutDescription() {
        assertThrows(DemureBotException.class, () -> Parser.parse(
                "event /from 2023-10-10 1400 /to 2023-10-10 1600"
        ));
    }
}
