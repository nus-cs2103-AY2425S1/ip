package friendlybot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import friendlybot.command.AddCommand;
import friendlybot.command.BadCommand;
import friendlybot.command.Command;
import friendlybot.command.DateCommand;
import friendlybot.command.DeleteCommand;
import friendlybot.command.ExitCommand;
import friendlybot.command.FindCommand;
import friendlybot.command.HelpCommand;
import friendlybot.command.ListCommand;
import friendlybot.command.MarkCommand;

/**
 * JUnit Test Cases for Parser.
 * Used GitHub CoPilot to assist with generating test cases for Parser.
 */
public class ParserTest {

    @Test
    public void testParseExitCommand() {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(1, markCommand.getTaskNumber());
        assertTrue(markCommand.getMarkStatus());
    }

    @Test
    public void testParseUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(1, markCommand.getTaskNumber());
        assertFalse(markCommand.getMarkStatus());
    }

    @Test
    public void testParseDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(1, deleteCommand.getTaskNumber());
    }

    @Test
    public void testParseTodoCommand() {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("todo", addCommand.getTaskType());
        assertEquals("read book", addCommand.getTaskDescription());
    }

    @Test
    public void testParseDeadlineCommand() {
        Command command = Parser.parse("deadline submit report /by 2023-12-31");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("deadline", addCommand.getTaskType());
        assertEquals("submit report", addCommand.getTaskDescription());
        assertEquals(LocalDate.of(2023, 12, 31), addCommand.getDeadlineDate());
    }

    @Test
    public void testParseEventCommand() {
        Command command = Parser.parse("event project meeting /from 2023-12-01 /to 2023-12-02");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("event", addCommand.getTaskType());
        assertEquals("project meeting", addCommand.getTaskDescription());
        assertEquals(LocalDate.of(2023, 12, 1), addCommand.getEventFromDate());
        assertEquals(LocalDate.of(2023, 12, 2), addCommand.getEventToDate());
    }

    @Test
    public void testParseDateCommand() {
        Command command = Parser.parse("date 2023-12-31");
        assertInstanceOf(DateCommand.class, command);
        DateCommand dateCommand = (DateCommand) command;
        assertEquals(LocalDate.of(2023, 12, 31), dateCommand.getDate());
    }

    @Test
    public void testParseFindCommand() {
        Command command = Parser.parse("find book");
        assertInstanceOf(FindCommand.class, command);
        FindCommand findCommand = (FindCommand) command;
        assertEquals("book", findCommand.getKeyword());
    }

    @Test
    public void testParseHelpCommand() {
        Command command = Parser.parse("help");
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        Command command = Parser.parse("invalid command");
        assertInstanceOf(BadCommand.class, command);
    }

    @Test
    public void testParseInvalidDate() {
        Command command = Parser.parse("deadline submit report /by invalid-date");
        assertInstanceOf(BadCommand.class, command);
    }

    @Test
    public void testParseInvalidEvent() {
        Command command = Parser.parse("event project meeting /from 2023-12-01 /to invalid-date");
        assertInstanceOf(BadCommand.class, command);
    }
}
