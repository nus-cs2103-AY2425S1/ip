package colress.parser;

import static colress.testutil.TestUtil.DELIMITER;
import static colress.testutil.TestUtil.EMPTY_STRING;
import static colress.testutil.TestUtil.INVALID_COMMAND;
import static colress.testutil.TestUtil.INVALID_DATE_ARGUMENT;
import static colress.testutil.TestUtil.INVALID_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.INVALID_TASK_TYPE_ARGUMENT;
import static colress.testutil.TestUtil.INVALID_TIME_ARGUMENT;
import static colress.testutil.TestUtil.VALID_COMMAND_ADD;
import static colress.testutil.TestUtil.VALID_COMMAND_CHECK;
import static colress.testutil.TestUtil.VALID_COMMAND_DATE;
import static colress.testutil.TestUtil.VALID_COMMAND_DELETE;
import static colress.testutil.TestUtil.VALID_COMMAND_EXIT;
import static colress.testutil.TestUtil.VALID_COMMAND_FIND;
import static colress.testutil.TestUtil.VALID_COMMAND_LIST;
import static colress.testutil.TestUtil.VALID_COMMAND_TOGGLE;
import static colress.testutil.TestUtil.VALID_COMMAND_UNCHECK;
import static colress.testutil.TestUtil.VALID_DATE_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_ONE;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER_ARGUMENT;
import static colress.testutil.TestUtil.VALID_TASK_TYPE_ARGUMENT_DEADLINE;
import static colress.testutil.TestUtil.VALID_TASK_TYPE_ARGUMENT_EVENT;
import static colress.testutil.TestUtil.VALID_TASK_TYPE_ARGUMENT_TODO;
import static colress.testutil.TestUtil.VALID_TO_TIME_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_TO_TIME_ONE;
import static colress.testutil.TestUtil.WHITESPACE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import colress.TaskType;
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
    public void getCommand_validAddCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_ADD), new AddCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validCheckCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_CHECK), new CheckCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validDateCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_DATE), new DateCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validDeleteCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_DELETE), new DeleteCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validExitCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_EXIT), new ExitCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validFindCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_FIND), new FindCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validListCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_LIST), new ListCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validToggleCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_TOGGLE), new ToggleCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_validUncheckCommand_success() {
        try {
            assertEquals(parser.getCommand(VALID_COMMAND_UNCHECK), new UncheckCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void getCommand_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> parser.getCommand(INVALID_COMMAND));
    }

    @Test
    public void parseCommand_validAddCommand_success() {
        try {
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
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validCheckCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_CHECK + WHITESPACE + VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT),
                    new CheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}));
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validDateCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_DATE + WHITESPACE + VALID_DATE_ARGUMENT_ONE),
                    new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}));
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validDeleteCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_DELETE + WHITESPACE + VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT),
                    new DeleteCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}));
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validExitCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_EXIT), new ExitCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validFindCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_FIND + WHITESPACE + VALID_KEYWORD_ONE),
                    new FindCommand(new String[]{VALID_KEYWORD_ONE}));
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validListCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_LIST), new ListCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validToggleCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_TOGGLE), new ToggleCommand());
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_validUncheckCommand_success() {
        try {
            assertEquals(parser.parseCommand(VALID_COMMAND_UNCHECK + WHITESPACE + VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT),
                    new UncheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}));
        } catch (UnknownCommandException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> parser.parseCommand(INVALID_COMMAND));
    }

    @Test
    public void getTaskType_validToDoArgument_success() {
        try {
            assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_TODO), TaskType.TODO);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void getTaskType_validDeadlineArgument_success() {
        try {
            assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_DEADLINE), TaskType.DEADLINE);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void getTaskType_validEventArgument_success() {
        try {
            assertEquals(parser.getTaskType(VALID_TASK_TYPE_ARGUMENT_EVENT), TaskType.EVENT);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void getTaskType_emptyInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> parser.getTaskType(EMPTY_STRING));
    }

    @Test
    public void getTaskType_invalidInput_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> parser.getTaskType(INVALID_TASK_TYPE_ARGUMENT));
    }

    @Test
    public void readDate_validInput_success() {
        try {
            assertEquals(parser.readDate(VALID_DATE_ARGUMENT_ONE), VALID_DATE_ONE);
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void readDate_validInputWithWhitespaces_success() {
        try {
            assertEquals(parser.readDate(WHITESPACE + VALID_DATE_ARGUMENT_ONE + WHITESPACE), VALID_DATE_ONE);
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void readDate_invalidInput_exceptionThrown() {
        try {
            assertThrows(DateTimeParseException.class, () -> parser.readDate(INVALID_DATE_ARGUMENT));
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void readTime_validInput_success() {
        try {
            assertEquals(parser.readTime(VALID_TO_TIME_ARGUMENT_ONE), VALID_TO_TIME_ONE);
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void readTime_validInputWithWhitespaces_success() {
        try {
            assertEquals(parser.readTime(WHITESPACE + VALID_TO_TIME_ARGUMENT_ONE + WHITESPACE),
                    VALID_TO_TIME_ONE);
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void readTime_invalidInput_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> parser.readTime(INVALID_TIME_ARGUMENT));
    }

    @Test
    public void getTaskNumber_oneValidInput_success() {
        try {
            assertArrayEquals(parser.getTaskNumber(VALID_ONE_TASK_NUMBER_ARGUMENT), VALID_ONE_TASK_NUMBER);
        } catch (NumberFormatException e) {
            fail();
        }
    }

    @Test
    public void getTaskNumber_multipleValidInputs_success() {
        try {
            assertArrayEquals(parser.getTaskNumber(VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT), VALID_MULTIPLE_TASK_NUMBERS);
        } catch (NumberFormatException e) {
            fail();
        }
    }

    @Test
    public void getTaskNumber_invalidInput_exceptionThrown() {
        assertThrows(NumberFormatException.class, () -> parser.getTaskNumber(INVALID_TASK_NUMBERS_ARGUMENT));
    }

    @Test
    public void getTaskNumber_emptyInput_exceptionThrown() {
        assertThrows(NumberFormatException.class, () -> parser.getTaskNumber(EMPTY_STRING));
    }

    @Test
    public void getString_validInput_success() {
        try {
            assertEquals(parser.getString(VALID_KEYWORD_ONE), VALID_KEYWORD_ONE);
        } catch (EmptyInputException e) {
            fail();
        }
    }

    @Test
    public void getString_validInputWithWhitespaces_success() {
        try {
            assertEquals(parser.getString(WHITESPACE + VALID_KEYWORD_ONE + WHITESPACE), VALID_KEYWORD_ONE);
        } catch (EmptyInputException e) {
            fail();
        }
    }

    @Test
    public void getString_emptyInput_exceptionThrown() {
        assertThrows(EmptyInputException.class, () -> parser.getString(EMPTY_STRING));
    }
}
