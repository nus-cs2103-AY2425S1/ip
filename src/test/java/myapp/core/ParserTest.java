package myapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import myapp.command.Command;
import myapp.command.DeadlineCommand;
import myapp.command.DeleteCommand;
import myapp.command.EventCommand;
import myapp.command.ExitCommand;
import myapp.command.FindCommand;
import myapp.command.FixedDurationCommand;
import myapp.command.HelpCommand;
import myapp.command.ListOnCommand;
import myapp.command.MarkCommand;
import myapp.command.ToDoCommand;
import myapp.command.UnMarkCommand;
import myapp.exceptions.BingBongException;
import myapp.exceptions.InvalidArgumentException;
import myapp.exceptions.InvalidFormatException;
import myapp.exceptions.UnsupportedCommandException;


class ParserTest {

    @Test
    void parseCommand_validExitCommand_returnsExitCommand() throws BingBongException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void parseCommand_validHelpCommand_returnsHelpCommand() throws BingBongException {
        Command command = Parser.parseCommand("help");
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    void parseCommand_invalidCommand_throwsUnsupportedCommandException() {
        assertThrows(UnsupportedCommandException.class, () -> Parser.parseCommand("invalid"));
    }

    @Test
    void parseCommand_validListOnCommand_returnsListOnCommand() throws BingBongException {
        Command command = Parser.parseCommand("list on 12/12/2023");
        assertTrue(command instanceof ListOnCommand);
    }

    @Test
    void parseCommand_validFindCommand_returnsFindCommand() throws BingBongException {
        Command command = Parser.parseCommand("find keyword");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    void parseCommand_invalidFindCommand_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> Parser.parseCommand("find "));
    }

    @Test
    void parseCommand_validFixedDurationCommand_returnsFixedDurationCommand() throws BingBongException {
        Command command = Parser.parseCommand("fixed task /period 5");
        assertTrue(command instanceof FixedDurationCommand);
    }

    @Test
    void parseCommand_invalidFixedDurationCommand_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> Parser.parseCommand("fixed task /period -1"));
    }

    @Test
    void parseCommand_validMarkCommand_returnsMarkCommand() throws BingBongException {
        Command command = Parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void parseCommand_validUnmarkCommand_returnsUnmarkCommand() throws BingBongException {
        Command command = Parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnMarkCommand);
    }

    @Test
    void parseCommand_validDeleteCommand_returnsDeleteCommand() throws BingBongException {
        Command command = Parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void parseCommand_validTodoCommand_returnsToDoCommand() throws BingBongException {
        Command command = Parser.parseCommand("todo read book");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    void parseCommand_invalidTodoCommand_throwsInvalidArgumentException() {
        assertThrows(InvalidArgumentException.class, () -> Parser.parseCommand("todo "));
    }

    @Test
    void parseCommand_validDeadlineCommand_returnsDeadlineCommand() throws BingBongException {
        Command command = Parser.parseCommand("deadline finish project /by 12/12/2050 1800");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    void parseCommand_invalidDeadlineCommand_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> Parser.parseCommand(
                "deadline finish project /by invalid-date"));
    }

    @Test
    void parseCommand_validEventCommand_returnsEventCommand() throws BingBongException {
        Command command = Parser.parseCommand("event concert /from 12/12/2023 1800 /to 12/12/2050 2000");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    void parseCommand_invalidEventCommand_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> Parser.parseCommand(
                "event concert /from 12/12/2023 1800 /to invalid-time"));
    }

    @Test
    void parseDeadlineDateTime_validInput_returnsCorrectDateTime() throws BingBongException {
        LocalDateTime dateTime = Parser.parseDeadlineDateTime("deadline finish project /by 12/12/2050 1800");
        assertEquals(LocalDateTime.of(2050, 12, 12, 18, 0), dateTime);
    }

    @Test
    void parseDeadlineDateTime_invalidInput_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> Parser.parseDeadlineDateTime(
                "deadline finish project /by invalid-date"));
    }

    @Test
    void parseEventDateTime_validInput_returnsCorrectDateTimeArray() throws BingBongException {
        LocalDateTime[] dateTimes = Parser.parseEventDateTime(
                "event concert /from 12/12/2050 1800 /to 12/12/2050 2000");
        assertEquals(LocalDateTime.of(2050, 12, 12, 18, 0), dateTimes[0]);
        assertEquals(LocalDateTime.of(2050, 12, 12, 20, 0), dateTimes[1]);
    }

    @Test
    void parseEventDateTime_invalidInput_throwsInvalidFormatException() {
        assertThrows(InvalidFormatException.class, () -> Parser.parseEventDateTime(
                "event concert /from 12/12/2023 1800 /to invalid-time"));
    }
}
