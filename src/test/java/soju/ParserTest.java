package soju;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import soju.commands.ByeCommand;
import soju.commands.Command;
import soju.commands.DeadlineCommand;
import soju.commands.DeleteCommand;
import soju.commands.EventCommand;
import soju.commands.FindCommand;
import soju.commands.ListCommand;
import soju.commands.MarkCommand;
import soju.commands.TodoCommand;
import soju.commands.UnmarkCommand;

public class ParserTest {

    @Test
    public void parse_byeCommand_returnsByeCommand() throws SojuException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws SojuException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parse_markCommand_returnsMarkCommand() throws SojuException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parse_unmarkCommand_returnsUnmarkCommand() throws SojuException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws SojuException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void parse_todoCommand_returnsTodoCommand() throws SojuException {
        Command command = Parser.parse("todo read a book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_deadlineCommand_returnsDeadlineCommand() throws SojuException {
        Command command = Parser.parse("deadline project /by 2023-09-25");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void parse_eventCommand_returnsEventCommand() throws SojuException {
        Command command = Parser.parse("event meeting /from 2023-09-25 0800 /to 2023-09-25 1000");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void parse_findCommand_returnsFindCommand() throws SojuException {
        Command command = Parser.parse("find project");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    public void parse_unknownCommand_throwsSojuException() {
        assertThrowsExactly(SojuException.class, () -> Parser.parse("unknownCommand"));
    }
}
