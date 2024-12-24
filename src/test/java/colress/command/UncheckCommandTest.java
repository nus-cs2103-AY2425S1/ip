package colress.command;

import static colress.testutil.TestUtil.INVALID_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.OUT_OF_BOUNDS_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER_ARGUMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.UiAdvanced;
import colress.UiBeginner;
import colress.parser.Parser;
import colress.tasklist.TaskList;
import colress.testutil.UiAdvancedStub;
import colress.testutil.UiBeginnerStub;

public class UncheckCommandTest {
    @Test
    public void startTest() {
        UncheckCommand uncheckCommand = new UncheckCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedString = colressUiBeginner.promptTaskNumber(taskList);
        assertEquals(expectedString, uncheckCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiBeginner_success() {
        UncheckCommand uncheckCommand = new UncheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedResult = UncheckCommand.MESSAGE_SUCCESSFUL_EXECUTION + "\n\n" + taskList.retrieveTasks();
        assertEquals(expectedResult, uncheckCommand.execute(colressUiBeginner,
                colressUiBeginner.getColress().getTaskList()));
    }

    @Test
    public void execute_uiAdvanced_success() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // valid input
        UncheckCommand uncheckCommand = new UncheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        String expectedResult = UncheckCommand.MESSAGE_SUCCESSFUL_EXECUTION + "\n\n" + taskList.retrieveTasks();
        assertEquals(expectedResult, uncheckCommand.execute(colressUiAdvanced, taskList));

        // not a number input
        uncheckCommand = new UncheckCommand(new String[]{INVALID_TASK_NUMBERS_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(false, true);

        expectedResult = UiAdvancedStub.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        assertEquals(expectedResult, uncheckCommand.execute(colressUiAdvanced, taskList));

        // index out of bounds
        uncheckCommand = new UncheckCommand(new String[]{OUT_OF_BOUNDS_TASK_NUMBERS_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(true, false);
        taskList = colressUiAdvanced.getColress().getTaskList();

        expectedResult = UiAdvancedStub.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        assertEquals(expectedResult, uncheckCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        UncheckCommand uncheckCommand = new UncheckCommand();

        String expectedString = Parser.COMMAND_UNCHECK;
        assertEquals(expectedString, uncheckCommand.toString());
    }

    @Test
    public void equalsTest() {
        UncheckCommand uncheckCommand = new UncheckCommand();

        // null values -> returns true
        assertTrue(uncheckCommand.equals(new UncheckCommand()));

        uncheckCommand = new UncheckCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        // same values -> returns true
        assertTrue(uncheckCommand.equals(new UncheckCommand(
                new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS)));

        // same object -> returns true
        assertTrue(uncheckCommand.equals(uncheckCommand));

        // null -> returns false
        assertFalse(uncheckCommand.equals(null));

        // different types -> returns false
        assertFalse(uncheckCommand.equals(17));

        // different values -> returns false
        assertFalse(uncheckCommand.equals(new UncheckCommand(
                new String[]{VALID_ONE_TASK_NUMBER_ARGUMENT}, VALID_ONE_TASK_NUMBER)));
    }
}
