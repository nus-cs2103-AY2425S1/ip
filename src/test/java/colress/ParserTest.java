package colress;

import static colress.TestUtil.DELIMITER;
import static colress.TestUtil.INVALID_COMMAND;
import static colress.TestUtil.INVALID_DATE_ARGUMENT;
import static colress.TestUtil.INVALID_TASK_NUMBERS_ARGUMENT;
import static colress.TestUtil.INVALID_TASK_TYPE_ARGUMENT;
import static colress.TestUtil.INVALID_TIME_ARGUMENT;
import static colress.TestUtil.VALID_COMMAND_ADD;
import static colress.TestUtil.VALID_COMMAND_CHECK;
import static colress.TestUtil.VALID_COMMAND_DATE;
import static colress.TestUtil.VALID_COMMAND_DELETE;
import static colress.TestUtil.VALID_COMMAND_EXIT;
import static colress.TestUtil.VALID_COMMAND_FIND;
import static colress.TestUtil.VALID_COMMAND_LIST;
import static colress.TestUtil.VALID_COMMAND_TOGGLE;
import static colress.TestUtil.VALID_COMMAND_UNCHECK;
import static colress.TestUtil.VALID_DATE_ARGUMENT_ONE;
import static colress.TestUtil.VALID_DATE_ONE;
import static colress.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.TestUtil.VALID_FROM_TIME_ARGUMENT_ONE;
import static colress.TestUtil.VALID_KEYWORD_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_ARGUMENT_TWO;
import static colress.TestUtil.VALID_TASK_NUMBERS_ONE;
import static colress.TestUtil.VALID_TASK_NUMBERS_TWO;
import static colress.TestUtil.VALID_TASK_TYPE_ARGUMENT_DEADLINE;
import static colress.TestUtil.VALID_TASK_TYPE_ARGUMENT_EVENT;
import static colress.TestUtil.VALID_TASK_TYPE_ARGUMENT_TODO;
import static colress.TestUtil.VALID_TO_TIME_ARGUMENT_ONE;
import static colress.TestUtil.VALID_TO_TIME_ONE;
import static colress.TestUtil.WHITESPACE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import colress.command.AddCommand;
import colress.command.CheckCommand;
import colress.command.DateCommand;
import colress.command.DeleteCommand;
import colress.command.ExitCommand;
import colress.command.FindCommand;
import colress.command.ListCommand;
import colress.command.ToggleCommand;
import colress.command.UncheckCommand;
import colress.exception.EmptyInputException;
import colress.exception.UnknownCommandException;

public class ParserTest {
    @Test
    public void getCommandTest() throws UnknownCommandException {
        Parser parser = new Parser();

        // AddCommand
        assertEquals(parser.getCommand(VALID_COMMAND_ADD), new AddCommand());

        // CheckCommand
        assertEquals(parser.getCommand(VALID_COMMAND_CHECK), new CheckCommand());

        // DateCommand
        assertEquals(parser.getCommand(VALID_COMMAND_DATE), new DateCommand());

        // DeleteCommand
        assertEquals(parser.getCommand(VALID_COMMAND_DELETE), new DeleteCommand());

        // ExitCommand
        assertEquals(parser.getCommand(VALID_COMMAND_EXIT), new ExitCommand());

        // FindCommand
        assertEquals(parser.getCommand(VALID_COMMAND_FIND), new FindCommand());

        // ListCommand
        assertEquals(parser.getCommand(VALID_COMMAND_LIST), new ListCommand());

        // ToggleCommand
        assertEquals(parser.getCommand(VALID_COMMAND_TOGGLE), new ToggleCommand());

        // UncheckCommand
        assertEquals(parser.getCommand(VALID_COMMAND_UNCHECK), new UncheckCommand());

        // Unknown command
        assertThrows(UnknownCommandException.class, () -> parser.getCommand(INVALID_COMMAND));
    }

    @Test
    public void parseCommandTest() throws UnknownCommandException {
        Parser parser = new Parser();

        // AddCommand
        // To-Do
        assertEquals(parser.parseCommand(VALID_COMMAND_ADD + WHITESPACE + VALID_TASK_TYPE_ARGUMENT_TODO
                        + DELIMITER + VALID_DESCRIPTION_ONE),
                new AddCommand(new String[]{VALID_TASK_TYPE_ARGUMENT_TODO, VALID_DESCRIPTION_ONE}));

        // Deadline
        assertEquals(parser.parseCommand(VALID_COMMAND_ADD + WHITESPACE + VALID_TASK_TYPE_ARGUMENT_DEADLINE
                        + DELIMITER + VALID_DESCRIPTION_ONE + DELIMITER + VALID_DATE_ARGUMENT_ONE),
                new AddCommand(new String[]{VALID_TASK_TYPE_ARGUMENT_DEADLINE, VALID_DESCRIPTION_ONE,
                    VALID_DATE_ARGUMENT_ONE}));

        // Event
        assertEquals(parser.parseCommand(VALID_COMMAND_ADD + WHITESPACE + VALID_TASK_TYPE_ARGUMENT_EVENT
                + DELIMITER + VALID_DESCRIPTION_ONE + DELIMITER + VALID_DATE_ARGUMENT_ONE + DELIMITER
                        + VALID_FROM_TIME_ARGUMENT_ONE + DELIMITER + VALID_TO_TIME_ARGUMENT_ONE),
                new AddCommand(new String[]{VALID_TASK_TYPE_ARGUMENT_EVENT, VALID_DESCRIPTION_ONE,
                    VALID_DATE_ARGUMENT_ONE, VALID_FROM_TIME_ARGUMENT_ONE, VALID_TO_TIME_ARGUMENT_ONE}));

        // CheckCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_CHECK + WHITESPACE + VALID_TASK_NUMBERS_ARGUMENT_ONE),
                new CheckCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}));

        // DateCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_DATE + WHITESPACE + VALID_DATE_ARGUMENT_ONE),
                new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}));

        // DeleteCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_DELETE + WHITESPACE + VALID_TASK_NUMBERS_ARGUMENT_ONE),
                new DeleteCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}));

        // ExitCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_EXIT), new ExitCommand());

        // FindCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_FIND + WHITESPACE + VALID_KEYWORD_ONE),
                new FindCommand(new String[]{VALID_KEYWORD_ONE}));

        // ListCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_LIST), new ListCommand());

        // ToggleCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_TOGGLE), new ToggleCommand());

        // UncheckCommand
        assertEquals(parser.parseCommand(VALID_COMMAND_UNCHECK + WHITESPACE + VALID_TASK_NUMBERS_ARGUMENT_ONE),
                new UncheckCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}));

        // Unknown command
        assertThrows(UnknownCommandException.class, () -> parser.parseCommand(INVALID_COMMAND));
    }

    @Test
    public void getTaskTypeTest() throws IllegalArgumentException {
        Parser parser = new Parser();

        // To-Do
        assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_TODO), TaskType.TODO);

        // Deadline
        assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_DEADLINE), TaskType.DEADLINE);

        // Event
        assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_EVENT), TaskType.EVENT);

        // empty input
        assertThrows(IllegalArgumentException.class, () -> parser.getTaskType(""));

        // Invalid input
        assertThrows(IllegalArgumentException.class, () -> parser.getTaskType(INVALID_TASK_TYPE_ARGUMENT));
    }

    @Test
    public void readDateTest() throws DateTimeParseException {
        Parser parser = new Parser();

        // valid input
        assertEquals(parser.readDate(VALID_DATE_ARGUMENT_ONE), VALID_DATE_ONE);

        // valid input with whitespaces
        assertEquals(parser.readDate(WHITESPACE + VALID_DATE_ARGUMENT_ONE + WHITESPACE), VALID_DATE_ONE);

        // invalid input
        assertThrows(DateTimeParseException.class, () -> parser.readDate(INVALID_DATE_ARGUMENT));
    }

    @Test
    public void readTimeTest() throws DateTimeParseException {
        Parser parser = new Parser();

        // valid input
        assertEquals(parser.readTime(VALID_TO_TIME_ARGUMENT_ONE), VALID_TO_TIME_ONE);

        // valid input with whitespaces
        assertEquals(parser.readTime(WHITESPACE + VALID_TO_TIME_ARGUMENT_ONE + WHITESPACE), VALID_TO_TIME_ONE);

        // invalid input
        assertThrows(DateTimeParseException.class, () -> parser.readTime(INVALID_TIME_ARGUMENT));
    }

    @Test
    public void getTaskNumberTest() throws NumberFormatException {
        Parser parser = new Parser();

        // one valid input
        assertArrayEquals(parser.getTaskNumber(VALID_TASK_NUMBERS_ARGUMENT_TWO), VALID_TASK_NUMBERS_TWO);

        // multiple valid inputs
        assertArrayEquals(parser.getTaskNumber(VALID_TASK_NUMBERS_ARGUMENT_ONE), VALID_TASK_NUMBERS_ONE);

        // invalid input
        assertThrows(NumberFormatException.class, () -> parser.getTaskNumber(INVALID_TASK_NUMBERS_ARGUMENT));

        // empty input
        assertThrows(NumberFormatException.class, () -> parser.getTaskNumber(""));
    }

    @Test
    public void getStringTest() throws EmptyInputException {
        Parser parser = new Parser();

        // valid input
        assertEquals(parser.getString(VALID_KEYWORD_ONE), VALID_KEYWORD_ONE);

        // valid input with whitespaces
        assertEquals(parser.getString(WHITESPACE + VALID_KEYWORD_ONE + WHITESPACE), VALID_KEYWORD_ONE);

        // empty input
        assertThrows(EmptyInputException.class, () -> parser.getString(""));
    }
}
