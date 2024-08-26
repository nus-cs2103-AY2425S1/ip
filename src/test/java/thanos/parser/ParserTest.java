package thanos.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import thanos.commands.ByeCommand;
import thanos.commands.Command;
import thanos.commands.DateCommand;
import thanos.commands.DeadlineCommand;
import thanos.commands.DeleteCommand;
import thanos.commands.EventCommand;
import thanos.commands.FindCommand;
import thanos.commands.ListCommand;
import thanos.commands.MarkCommand;
import thanos.commands.TodoCommand;
import thanos.commands.UnmarkCommand;
import thanos.exceptions.InvalidCommandException;

public class ParserTest {
    @Test
    public void parse_emptyInput_throwsInvalidCommandException() {
        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class,
                () -> Parser.parse(""),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("No input provided. Please enter a command.", exception.getMessage());
    }

    @Test
    public void parse_invalidCommand_throwsInvalidCommandException() {
        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class,
                () -> Parser.parse("test test"),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Oops! I don't recognise the command you entered. Please enter a valid command.",
                exception.getMessage());
    }

    @Test
    public void parse_bye_returnsByeCommand() throws InvalidCommandException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ByeCommand.class, command, "Expected a ByeCommand");
    }

    @Test
    public void parse_list_returnsListCommand() throws InvalidCommandException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command, "Expected a ListCommand");
    }

    @Test
    public void parse_todo_returnsTodoCommand() throws InvalidCommandException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command, "Expected a TodoCommand");
    }

    @Test
    public void parse_deadline_returnsDeadlineCommand() throws InvalidCommandException {
        Command command = Parser.parse("deadline return book /by 2024-08-24 1800");
        assertInstanceOf(DeadlineCommand.class, command, "Expected a DeadlineCommand");
    }

    @Test
    public void parse_event_returnsEventCommand() throws InvalidCommandException {
        Command command = Parser.parse("event project meeting /from 2024-08-24 1400 /to 2024-08-24 1600");
        assertInstanceOf(EventCommand.class, command, "Expected an EventCommand");
    }

    @Test
    public void parse_delete_returnsDeleteCommand() throws InvalidCommandException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command, "Expected a DeleteCommand");
    }

    @Test
    public void parse_mark_returnsMarkCommand() throws InvalidCommandException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command, "Expected a MarkCommand");
    }

    @Test
    public void parse_unmark_returnsUnmarkCommand() throws InvalidCommandException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command, "Expected an UnmarkCommand");
    }

    @Test
    public void parse_find_returnsFindCommand() throws InvalidCommandException {
        Command command = Parser.parse("find read book");
        assertInstanceOf(FindCommand.class, command, "Expected a FindCommand");
    }

    @Test
    public void parse_date_returnsDateCommand() throws InvalidCommandException {
        Command command = Parser.parse("date 12-08-2024");
        assertInstanceOf(DateCommand.class, command, "Expected a DateCommand");
    }
}
