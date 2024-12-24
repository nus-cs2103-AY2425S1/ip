package colress.command;

import static colress.Ui.MESSAGE_LIST_EMPTY;
import static colress.Ui.MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
import static colress.testutil.TestUtil.INVALID_DATE_ARGUMENT;
import static colress.testutil.TestUtil.VALID_DATE_ARGUMENT_NONE;
import static colress.testutil.TestUtil.VALID_DATE_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_DATE_ARGUMENT_TWO;
import static colress.testutil.TestUtil.VALID_DATE_NONE;
import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.TaskType;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;
import colress.testutil.UiAdvancedStub;
import colress.testutil.UiBeginnerStub;

public class DateCommandTest {
    @Test
    public void startTest() {
        DateCommand dateCommand = new DateCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedString = colressUiBeginner.promptDate(TaskType.TODO, taskList);
        assertEquals(expectedString, dateCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiBeginner_success() {
        DateCommand dateCommand = new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}, VALID_DATE_ONE);
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedResult = taskList.retrieveTasks(VALID_DATE_ONE);
        assertEquals(expectedResult, dateCommand.execute(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiAdvancedInvalidFormat_exceptionThrown() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // no arguments -> exception thrown
        DateCommand dateCommandNoArguments = new DateCommand();

        assertThrows(InvalidCommandFormatException.class, () ->
                dateCommandNoArguments.execute(colressUiAdvanced, taskList));

        // too many arguments -> exception thrown
        DateCommand dateCommandTooManyArguments = new DateCommand(new String[]
            {VALID_DATE_ARGUMENT_ONE, VALID_DATE_ARGUMENT_ONE});

        assertThrows(InvalidCommandFormatException.class, () ->
                dateCommandTooManyArguments.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void execute_uiAdvanced_success() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // valid input, non-empty result
        DateCommand dateCommand = new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}, VALID_DATE_ONE);

        String expectedResult = taskList.retrieveTasks(VALID_DATE_ONE);
        assertEquals(expectedResult, dateCommand.execute(colressUiAdvanced, taskList));

        // valid input, empty result
        dateCommand = new DateCommand(new String[]{VALID_DATE_ARGUMENT_NONE}, VALID_DATE_NONE);

        expectedResult = MESSAGE_LIST_EMPTY;
        assertEquals(expectedResult, dateCommand.execute(colressUiAdvanced, taskList));

        // invalid input
        dateCommand = new DateCommand(new String[]{INVALID_DATE_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(true, false);

        expectedResult = MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        assertEquals(expectedResult, dateCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        DateCommand dateCommand = new DateCommand();

        String expectedString = Parser.COMMAND_DATE;
        assertEquals(expectedString, dateCommand.toString());
    }

    @Test
    public void equalsTest() {
        DateCommand dateCommand = new DateCommand();

        // null values -> returns true
        assertTrue(dateCommand.equals(new DateCommand()));

        dateCommand = new DateCommand(new String[]{VALID_DATE_ARGUMENT_ONE}, VALID_DATE_ONE);

        // same values -> returns true
        assertTrue(dateCommand.equals(new DateCommand(
                new String[]{VALID_DATE_ARGUMENT_ONE}, VALID_DATE_ONE)));

        // same object -> returns true
        assertTrue(dateCommand.equals(dateCommand));

        // null -> returns false
        assertFalse(dateCommand.equals(null));

        // different types -> returns false
        assertFalse(dateCommand.equals(17));

        // different values -> returns false
        assertFalse(dateCommand.equals(new DateCommand(
                new String[]{VALID_DATE_ARGUMENT_TWO}, VALID_DATE_TWO)));
    }
}
