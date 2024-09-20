package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.Deadline;
import task.Event;
import task.ToDo;

public class ParserTest {
    @Test
    public void testParseValidListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseValidTodoCommand() {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        ToDo todo = (ToDo) addCommand.getTask();
        assertEquals("read book", todo.getDescription());
    }

    @Test
    public void testParseValidDeadlineCommand() {
        Command command = Parser.parse("deadline return book /by 2021-09-30 1800");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        Deadline deadline = (Deadline) addCommand.getTask();
        assertEquals("return book", deadline.getDescription());
    }

    @Test
    public void testParseInvalidDeadlineCommand() {
        Command command = Parser.parse("deadline return book");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void testParseValidEventCommand() {
        Command command = Parser.parse("event project meeting /from 2021-09-30 1800 /to 2021-09-30 2000");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        Event event = (Event) addCommand.getTask();
        assertEquals("project meeting", event.getDescription());
    }

    @Test
    public void testParseInvalidEventCommand() {
        Command command = Parser.parse("event project meeting /from 2021-09-30 1800");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void testParseValidMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseValidUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseValidDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseValidExitCommand() {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        Command command = Parser.parse("invalid command");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void testParseEmptyCommand() {
        Command command = Parser.parse("");
        assertInstanceOf(InvalidCommand.class, command);
    }
}
