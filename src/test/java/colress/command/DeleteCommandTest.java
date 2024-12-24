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

public class DeleteCommandTest {
    @Test
    public void startTest() {
        DeleteCommand deleteCommand = new DeleteCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedString = colressUiBeginner.promptTaskNumber(taskList);
        assertEquals(expectedString, deleteCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiBeginner_success() {
        DeleteCommand deleteCommand = new DeleteCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedResult = DeleteCommand.MESSAGE_SUCCESSFUL_EXECUTION + "\n\n" + taskList.retrieveTasks();
        assertEquals(expectedResult, deleteCommand.execute(colressUiBeginner,
                colressUiBeginner.getColress().getTaskList()));
    }

    @Test
    public void execute_uiAdvanced_success() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // valid input
        DeleteCommand deleteCommand = new DeleteCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        String expectedResult = DeleteCommand.MESSAGE_SUCCESSFUL_EXECUTION + "\n\n" + taskList.retrieveTasks();
        assertEquals(expectedResult, deleteCommand.execute(colressUiAdvanced, taskList));

        // not a number input
        deleteCommand = new DeleteCommand(new String[]{INVALID_TASK_NUMBERS_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(false, true);

        expectedResult = UiAdvancedStub.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        assertEquals(expectedResult, deleteCommand.execute(colressUiAdvanced, taskList));

        // index out of bounds
        deleteCommand = new DeleteCommand(new String[]{OUT_OF_BOUNDS_TASK_NUMBERS_ARGUMENT});
        colressUiAdvanced = new UiAdvancedStub(true, false);
        taskList = colressUiAdvanced.getColress().getTaskList();

        expectedResult = UiAdvancedStub.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        assertEquals(expectedResult, deleteCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        DeleteCommand deleteCommand = new DeleteCommand();

        String expectedString = Parser.COMMAND_DELETE;
        assertEquals(expectedString, deleteCommand.toString());
    }

    @Test
    public void equalsTest() {
        DeleteCommand deleteCommand = new DeleteCommand();

        // null values -> returns true
        assertTrue(deleteCommand.equals(new DeleteCommand()));

        deleteCommand = new DeleteCommand(new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT},
                VALID_MULTIPLE_TASK_NUMBERS);

        // same values -> returns true
        assertTrue(deleteCommand.equals(new DeleteCommand(
                new String[]{VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT}, VALID_MULTIPLE_TASK_NUMBERS)));

        // same object -> returns true
        assertTrue(deleteCommand.equals(deleteCommand));

        // null -> returns false
        assertFalse(deleteCommand.equals(null));

        // different types -> returns false
        assertFalse(deleteCommand.equals(17));

        // different values -> returns false
        assertFalse(deleteCommand.equals(new DeleteCommand(
                new String[]{VALID_ONE_TASK_NUMBER_ARGUMENT}, VALID_ONE_TASK_NUMBER)));
    }
}
