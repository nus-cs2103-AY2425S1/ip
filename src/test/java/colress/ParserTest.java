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
    private final Parser parser = new Parser();

    @Test
    public void getCommand_validAddCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_ADD), new AddCommand());
    }

    @Test
    public void getCommand_validCheckCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_CHECK), new CheckCommand());
    }

    @Test
    public void getCommand_validDateCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_DATE), new DateCommand());
    }

    @Test
    public void getCommand_validDeleteCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_DELETE), new DeleteCommand());
    }

    @Test
    public void getCommand_validExitCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_EXIT), new ExitCommand());
    }

    @Test
    public void getCommand_validFindCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_FIND), new FindCommand());
    }

    @Test
    public void getCommand_validListCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_LIST), new ListCommand());
    }

    @Test
    public void getCommand_validToggleCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_TOGGLE), new ToggleCommand());
    }

    @Test
    public void getCommand_validUncheckCommand_success() throws UnknownCommandException {
        assertEquals(parser.getCommand(VALID_COMMAND_UNCHECK), new UncheckCommand());
    }

    @Test
    public void getCommand_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> parser.getCommand(INVALID_COMMAND));
    }

    @Test
    public void parseCommand_validAddCommand_success() throws UnknownCommandException {
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
    }

    @Test
    public void parseCommand_validCheckCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_CHECK + WHITESPACE + VALID_TASK_NUMBERS_ARGUMENT_ONE),
                new CheckCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}));
    }

    @Test
    public void parseCommand_validDateCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_DATE + WHITESPACE + VALID_DATE_ARGUMENT_ONE),
                new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}));
    }

    @Test
    public void parseCommand_validDeleteCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_DELETE + WHITESPACE + VALID_TASK_NUMBERS_ARGUMENT_ONE),
                new DeleteCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}));
    }

    @Test
    public void parseCommand_validExitCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_EXIT), new ExitCommand());
    }

    @Test
    public void parseCommand_validFindCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_FIND + WHITESPACE + VALID_KEYWORD_ONE),
                new FindCommand(new String[]{VALID_KEYWORD_ONE}));
    }

    @Test
    public void parseCommand_validListCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_LIST), new ListCommand());
    }

    @Test
    public void parseCommand_validToggleCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_TOGGLE), new ToggleCommand());
    }

    @Test
    public void parseCommand_validUncheckCommand_success() throws UnknownCommandException {
        assertEquals(parser.parseCommand(VALID_COMMAND_UNCHECK + WHITESPACE + VALID_TASK_NUMBERS_ARGUMENT_ONE),
                new UncheckCommand(new String[]{VALID_TASK_NUMBERS_ARGUMENT_ONE}));
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> parser.parseCommand(INVALID_COMMAND));
    }

    @Test
    public void getTaskType_validToDoArgument_success() throws IllegalArgumentException {
        assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_TODO), TaskType.TODO);
    }

    @Test
    public void getTaskType_validDeadlineArgument_success() throws IllegalArgumentException {
        assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_DEADLINE), TaskType.DEADLINE);
    }

    @Test
    public void getTaskType_validEventArgument_success() throws IllegalArgumentException {
        assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_EVENT), TaskType.EVENT);
    }

    @Test
    public void getTaskType_emptyInput_exceptionThrown() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> parser.getTaskType(""));
    }

    @Test
    public void getTaskType_invalidInput_exceptionThrown() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> parser.getTaskType(INVALID_TASK_TYPE_ARGUMENT));
    }

    @Test
    public void readDate_validInput_success() throws DateTimeParseException {
        assertEquals(parser.readDate(VALID_DATE_ARGUMENT_ONE), VALID_DATE_ONE);
    }

    @Test
    public void readDate_validInputWithWhitespaces_success() throws DateTimeParseException {
        assertEquals(parser.readDate(WHITESPACE + VALID_DATE_ARGUMENT_ONE + WHITESPACE), VALID_DATE_ONE);
    }

    @Test
    public void readDate_invalidInput_exceptionThrown() throws DateTimeParseException {
        assertThrows(DateTimeParseException.class, () -> parser.readDate(INVALID_DATE_ARGUMENT));
    }

    @Test
    public void readTime_validInput_success() throws DateTimeParseException {
        assertEquals(parser.readTime(VALID_TO_TIME_ARGUMENT_ONE), VALID_TO_TIME_ONE);
    }

    @Test
    public void readTime_validInputWithWhitespaces_success() throws DateTimeParseException {
        assertEquals(parser.readTime(WHITESPACE + VALID_TO_TIME_ARGUMENT_ONE + WHITESPACE), VALID_TO_TIME_ONE);
    }

    @Test
    public void readTime_invalidInput_exceptionThrown() throws DateTimeParseException {
        assertThrows(DateTimeParseException.class, () -> parser.readTime(INVALID_TIME_ARGUMENT));
    }

    @Test
    public void getTaskNumber_oneValidInput_success() throws NumberFormatException {
        assertArrayEquals(parser.getTaskNumber(VALID_TASK_NUMBERS_ARGUMENT_TWO), VALID_TASK_NUMBERS_TWO);
    }

    @Test
    public void getTaskNumber_multipleValidInputs_success() throws NumberFormatException {
        assertArrayEquals(parser.getTaskNumber(VALID_TASK_NUMBERS_ARGUMENT_ONE), VALID_TASK_NUMBERS_ONE);
    }

    @Test
    public void getTaskNumber_invalidInput_exceptionThrown() throws NumberFormatException {
        assertThrows(NumberFormatException.class, () -> parser.getTaskNumber(INVALID_TASK_NUMBERS_ARGUMENT));
    }

    @Test
    public void getTaskNumber_emptyInput_exceptionThrown() throws NumberFormatException {
        assertThrows(NumberFormatException.class, () -> parser.getTaskNumber(""));
    }

    @Test
    public void getString_validInput_success() throws EmptyInputException {
        assertEquals(parser.getString(VALID_KEYWORD_ONE), VALID_KEYWORD_ONE);
    }

    @Test
    public void getString_validInputWithWhitespaces_success() throws EmptyInputException {
        assertEquals(parser.getString(WHITESPACE + VALID_KEYWORD_ONE + WHITESPACE), VALID_KEYWORD_ONE);
    }

    @Test
    public void getString_emptyInput_exceptionThrown() {
        assertThrows(EmptyInputException.class, () -> parser.getString(""));
    }
}
