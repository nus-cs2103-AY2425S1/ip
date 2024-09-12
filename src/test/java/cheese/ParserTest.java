package cheese;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cheese.command.AddCommand;
import cheese.command.Command;
import cheese.command.ExitCommand;
import cheese.command.FindCommand;
import cheese.command.ListCommand;
import cheese.command.MarkCommand;
import cheese.command.SnoozeCommand;
import cheese.command.UpdateCommand;
import cheese.exception.CheeseException;
import cheese.exception.InputException;


/**
 * Class to test Parser class in cheese package
 */
public class ParserTest {
    @Test
    public void parseByeCommandReturnsExitCommand() throws CheeseException {
        Command command = Parser.parse("bye", 0);
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void parseListCommandReturnsListCommand() throws CheeseException {
        Command command = Parser.parse("list", 0);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parseTodoCommandReturnsAddCommand() throws CheeseException {
        Command command = Parser.parse("todo read book", 0);
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parseDeadlineCommandReturnsAddCommand() throws CheeseException {
        Command command = Parser.parse("deadline submit report /by 2023-12-01", 0);
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parseEventCommandReturnsAddCommand() throws CheeseException {
        Command command = Parser.parse("event project meeting /from 2023-11-01 /to 2023-11-02", 0);
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parseMarkCommandReturnsMarkCommand() throws CheeseException {
        Command command = Parser.parse("mark 1", 5);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parseUnmarkCommandReturnsMarkCommand() throws CheeseException {
        Command command = Parser.parse("unmark 1", 5);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parseDeleteCommandReturnsUpdateCommand() throws CheeseException {
        Command command = Parser.parse("delete 3", 5);
        assertInstanceOf(UpdateCommand.class, command);
    }

    @Test
    public void parseFindReturnsFindCommand() throws CheeseException {
        Command command = Parser.parse("find a", 5);
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    public void parseRescheduleCommandReturnsSnoozeCommand() throws CheeseException {
        Command command = Parser.parse("reschedule 2 2024-12-15", 5);
        assertInstanceOf(SnoozeCommand.class, command);
    }

    @Test
    public void parseSnoozeCommandReturnsSnoozeCommand() throws CheeseException {
        Command command = Parser.parse("snooze 3 7", 5);
        assertInstanceOf(SnoozeCommand.class, command);
    }

    @Test
    public void parseInvalidCommandThrowsCheeseException() {
        assertThrows(CheeseException.class, () -> Parser.parse("invalid command", 5));
    }

    @Test
    public void parseMissingDeadlineDateThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("deadline submit report /by", 0);
        });
        assertTrue(exception.getMessage().contains("Missing a /by"));
    }

    @Test
    public void parseMissingEventFromDateThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("event team meeting /to 2023-12-10", 0);
        });
        assertTrue(exception.getMessage().contains("Missing /from"));
    }

    @Test
    public void parseMissingEventToDateThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("event team meeting /from 2023-12-01", 0);
        });
        assertTrue(exception.getMessage().contains("Missing /to"));
    }

    @Test
    public void parseInvalidEventDateFormatThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("event team meeting /from 2023-13-01 /to 2023-14-10", 0);
        });
        assertTrue(exception.getMessage().contains("YYYY-MM-DD"));
    }

    @Test
    public void parseMissingRescheduleDateThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("reschedule 2", 5);
        });
        assertTrue(exception.getMessage().contains("Missing argument"));
    }

    @Test
    public void parseRescheduleToPastDateThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("reschedule 2 2020-01-01", 5);
        });
        assertTrue(exception.getMessage().contains("Cannot reschedule to past date"));
    }

    @Test
    public void parseMissingSnoozeDaysThrowsInputException() {
        Exception exception = assertThrows(InputException.class, () -> {
            Parser.parse("snooze 2", 5);
        });
        assertTrue(exception.getMessage().contains("Insufficient len to find index"));
    }

    @Test
    public void parseInvalidTaskIndexThrowsInputException() {
        Exception exception = assertThrows(CheeseException.class, () -> {
            Parser.parse("mark 10", 5);
        });
        assertTrue(exception.getMessage().contains("Incorrect location of cheese"));
    }

    @Test
    public void parseInvalidTaskIndexTooSmallThrowsInputException() {
        Exception exception = assertThrows(CheeseException.class, () -> {
            Parser.parse("unmark -1", 5);
        });
        assertTrue(exception.getMessage().contains("Incorrect location of cheese"));
    }

    @Test
    public void parseInvalidTaskIndexNotANumberThrowsInputException() {
        Exception exception = assertThrows(CheeseException.class, () -> {
            Parser.parse("mark one", 5);
        });
        assertTrue(exception.getMessage().contains("For input string"));
    }

    @Test
    public void parseEmptyTodoDescriptionThrowsInputException() {
        Exception exception = assertThrows(CheeseException.class, () -> {
            Parser.parse("todo", 0);
        });
        assertTrue(exception.getMessage().contains("Cheese needs to have a name"));
    }

    @Test
    public void parseInvalidCommandThrowsInputException() {
        Exception exception = assertThrows(CheeseException.class, () -> {
            Parser.parse("unknown command", 0);
        });
        assertTrue(exception.getMessage().contains("Invalid"));
    }
}
