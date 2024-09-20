package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkTaskCommand;
import command.UnknownCommand;
import command.UnmarkTaskCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link Parser} class, ensuring that valid commands are parsed correctly
 * and the appropriate {@link Command} subclasses are returned.
 */
public class ParserTest {

    /**
     * Tests parsing the "bye" command, ensuring that an {@link ExitCommand} is returned.
     */
    @Test
    public void testParseByeCommand() {
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
    }

    /**
     * Tests parsing the "list" command, ensuring that a {@link ListCommand} is returned.
     */
    @Test
    public void testParseListCommand() {
        assertEquals(ListCommand.class, Parser.parse("list").getClass());
    }

    /**
     * Tests parsing the "find" command with an argument, ensuring that a {@link FindCommand} is returned.
     */
    @Test
    public void testParseFindCommand() {
        assertEquals(FindCommand.class, Parser.parse("find keyword").getClass());
    }

    /**
     * Tests parsing the "mark" command with a task number, ensuring that a {@link MarkTaskCommand} is returned.
     */
    @Test
    public void testParseMarkCommand() {
        assertEquals(MarkTaskCommand.class, Parser.parse("mark 1").getClass());
    }

    /**
     * Tests parsing the "unmark" command with a task number, ensuring that an {@link UnmarkTaskCommand} is returned.
     */
    @Test
    public void testParseUnmarkCommand() {
        assertEquals(UnmarkTaskCommand.class, Parser.parse("unmark 1").getClass());
    }

    /**
     * Tests parsing the "delete" command with a task number, ensuring that a {@link DeleteTaskCommand} is returned.
     */
    @Test
    public void testParseDeleteCommand() {
        assertEquals(DeleteTaskCommand.class, Parser.parse("delete 1").getClass());
    }

    /**
     * Tests parsing the "todo" command with a task description, ensuring that an {@link AddTodoCommand} is returned.
     */
    @Test
    public void testParseTodoCommand() {
        assertEquals(AddTodoCommand.class, Parser.parse("todo read book").getClass());
    }

    /**
     * Tests parsing the "deadline" command with a task description and deadline date,
     * ensuring that an {@link AddDeadlineCommand} is returned.
     */
    @Test
    public void testParseDeadlineCommand() {
        assertEquals(AddDeadlineCommand.class, Parser.parse("deadline return book /by 2024-09-30").getClass());
    }

    /**
     * Tests parsing the "event" command with a task description and event dates,
     * ensuring that an {@link AddEventCommand} is returned.
     */
    @Test
    public void testParseEventCommand() {
        assertEquals(AddEventCommand.class, Parser.parse("event meeting /from 2024-09-30 /to 2024-10-01").getClass());
    }

    /**
     * Tests parsing an invalid command, ensuring that an {@link UnknownCommand} is returned.
     */
    @Test
    public void testParseUnknownCommand() {
        assertEquals(UnknownCommand.class, Parser.parse("invalidCommand").getClass());
    }

    /**
     * Tests parsing the "todo" command with no task description,
     * ensuring that an {@link InvalidCommand} is returned.
     */
    @Test
    public void testParseInvalidTodoCommand() {
        assertEquals(InvalidCommand.class, Parser.parse("todo").getClass());
    }

    /**
     * Tests parsing the "deadline" command with an invalid format (no task description),
     * ensuring that an {@link InvalidCommand} is returned.
     */
    @Test
    public void testParseInvalidDeadlineCommand() {
        assertEquals(InvalidCommand.class, Parser.parse("deadline /by").getClass());
    }
}
