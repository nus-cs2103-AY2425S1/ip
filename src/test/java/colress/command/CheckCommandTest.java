package colress.command;

import static colress.testutil.TestUtil.INVALID_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.OUT_OF_BOUNDS_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER_ARGUMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;
import colress.testutil.UiAdvancedStub;
import colress.testutil.UiBeginnerStub;

public class CheckCommandTest {
    @Test
    public void startTest() {
        CheckCommand checkCommand = new CheckCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedString = colressUiBeginner.promptTaskNumber(taskList);
        assertEquals(expectedString, checkCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiBeginner_success() {
        CheckCommand checkCommand = new CheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedResult = CheckCommand.MESSAGE_SUCCESSFUL_EXECUTION + "\n\n" + taskList.retrieveTasks();
        assertEquals(expectedResult, checkCommand.execute(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiAdvancedInvalidFormat_exceptionThrown() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // no arguments -> exception thrown
        CheckCommand checkCommandNoArguments = new CheckCommand();

        assertThrows(InvalidCommandFormatException.class, () ->
                checkCommandNoArguments.execute(colressUiAdvanced, taskList));

        // too many arguments -> exception thrown
        CheckCommand checkCommandTooManyArguments = new CheckCommand(new String[]
            {VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT, VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT});

        assertThrows(InvalidCommandFormatException.class, () ->
                checkCommandTooManyArguments.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void execute_uiAdvanced_success() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // valid input
        CheckCommand checkCommand = new CheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        String expectedResult = CheckCommand.MESSAGE_SUCCESSFUL_EXECUTION + "\n\n" + taskList.retrieveTasks();
        assertEquals(expectedResult, checkCommand.execute(colressUiAdvanced, taskList));

        // not a number input
        checkCommand = new CheckCommand(new String[]{INVALID_TASK_NUMBERS_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(false, true);

        expectedResult = UiAdvancedStub.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        assertEquals(expectedResult, checkCommand.execute(colressUiAdvanced, taskList));

        // index out of bounds
        checkCommand = new CheckCommand(new String[]{OUT_OF_BOUNDS_TASK_NUMBERS_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(true, false);
        taskList = colressUiAdvanced.getColress().getTaskList();

        expectedResult = UiAdvancedStub.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        assertEquals(expectedResult, checkCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        CheckCommand checkCommand = new CheckCommand();

        String expectedString = Parser.COMMAND_CHECK;
        assertEquals(expectedString, checkCommand.toString());
    }

    @Test
    public void equalsTest() {
        CheckCommand checkCommand = new CheckCommand();

        // null values -> returns true
        assertTrue(checkCommand.equals(new CheckCommand()));

        checkCommand = new CheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        // same values -> returns true
        assertTrue(checkCommand.equals(new CheckCommand(
                new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS)));

        // same object -> returns true
        assertTrue(checkCommand.equals(checkCommand));

        // null -> returns false
        assertFalse(checkCommand.equals(null));

        // different types -> returns false
        assertFalse(checkCommand.equals(17));

        // different values -> returns false
        assertFalse(checkCommand.equals(new CheckCommand(
                new String[]{VALID_ONE_TASK_NUMBER_ARGUMENT}, VALID_ONE_TASK_NUMBER)));
    }
}
