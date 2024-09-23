package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SearchCommand;
import command.SortDeadlineCommand;
import command.UnmarkCommand;
import fridayexception.FridayException;
import fridayexception.InvalidDeadlineArgument;
import fridayexception.InvalidDeleteArgument;
import fridayexception.InvalidEventArgument;
import fridayexception.InvalidFindArgument;
import fridayexception.InvalidFridayCommand;
import fridayexception.InvalidMarkArgument;
import fridayexception.InvalidSearchArgument;
import fridayexception.InvalidTodoArgument;
import fridayexception.InvalidUnmarkArgument;


public class ParserTest {

    @Test
    public void parseUserInput_validTodoCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("todo task 1");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("task 1", addCommand.getTask().getTaskName());
    }

    @Test
    public void parseUserInput_invalidTodoCommand_throwsInvalidTodoArgument() {
        assertThrows(InvalidTodoArgument.class, () -> {
            Parser.parseUserInput("todo");
        });
    }

    @Test
    public void parseUserInput_validDeadlineCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("deadline task 2 /by 2025-10-10");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("[D][ ] task 2 (by: Oct 10 2025)", addCommand.getTask().toString());
    }

    @Test
    public void parseUserInput_invalidDeadlineCommand_throwsInvalidDeadlineArgument() {
        assertThrows(InvalidDeadlineArgument.class, () -> {
            Parser.parseUserInput("deadline task 2");
        });
    }

    @Test
    public void parseUserInput_validEventCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("event task 3 /from 2024-10-10 /to 2024-11-11");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("[E][ ] task 3 (from: Oct 10 2024 to: Nov 11 2024)", addCommand.getTask().toString());
    }

    @Test
    public void parseUserInput_invalidEventCommand_throwsInvalidEventArgument() {
        assertThrows(InvalidEventArgument.class, () -> {
            Parser.parseUserInput("event task 3 /from 2024-10-10");
        });
    }

    @Test
    public void parseUserInput_validMarkCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("mark 1");
        assertTrue(command instanceof MarkCommand);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(0, markCommand.getIndex());
    }

    @Test
    public void parseUserInput_invalidMarkCommand_throwsInvalidMarkArgument() {
        assertThrows(InvalidMarkArgument.class, () -> {
            Parser.parseUserInput("mark");
        });
    }

    @Test
    public void parseUserInput_validUnmarkCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
        UnmarkCommand unmarkCommand = (UnmarkCommand) command;
        assertEquals(0, unmarkCommand.getIndex());
    }

    @Test
    public void parseUserInput_invalidUnmarkCommand_throwsInvalidUnmarkArgument() {
        assertThrows(InvalidUnmarkArgument.class, () -> {
            Parser.parseUserInput("unmark");
        });
    }

    @Test
    public void parseUserInput_validDeleteCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("delete 1");
        assertTrue(command instanceof DeleteCommand);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(0, deleteCommand.getIndex());
    }

    @Test
    public void parseUserInput_invalidDeleteCommand_throwsInvalidDeleteArgument() {
        assertThrows(InvalidDeleteArgument.class, () -> {
            Parser.parseUserInput("delete");
        });
    }

    @Test
    public void parseUserInput_validSearchCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("search 2025-10-10");
        assertTrue(command instanceof SearchCommand);
        SearchCommand searchCommand = (SearchCommand) command;
        assertEquals(LocalDate.of(2025, 10, 10), searchCommand.getDate());
    }

    @Test
    public void parseUserInput_invalidSearchCommand_throwsInvalidSearchArgument() {
        assertThrows(InvalidSearchArgument.class, () -> {
            Parser.parseUserInput("search");
        });
    }

    @Test
    public void parseUserInput_validFindCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("find keyword");
        assertTrue(command instanceof FindCommand);
        FindCommand findCommand = (FindCommand) command;
        assertEquals("keyword", findCommand.getKeyword());
    }

    @Test
    public void parseUserInput_invalidFindCommand_throwsInvalidFindArgument() {
        assertThrows(InvalidFindArgument.class, () -> {
            Parser.parseUserInput("find");
        });
    }

    @Test
    public void parseUserInput_validSortCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("sort");
        assertTrue(command instanceof SortDeadlineCommand);
    }

    @Test
    public void parseUserInput_validExitCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseUserInput_invalidCommand_throwsInvalidFridayCommand() {
        assertThrows(InvalidFridayCommand.class, () -> {
            Parser.parseUserInput("invalidCommand");
        });
    }

    @Test
    public void parseUserInput_validListCommand_success() throws FridayException {
        Command command = Parser.parseUserInput("list");
        assertTrue(command instanceof ListCommand);
    }
}

