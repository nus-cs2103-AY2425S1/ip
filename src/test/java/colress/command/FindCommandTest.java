package colress.command;

import static colress.Ui.MESSAGE_LIST_EMPTY;
import static colress.testutil.TestUtil.EMPTY_STRING;
import static colress.testutil.TestUtil.VALID_KEYWORD_NONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_ONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.EmptyInputException;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;
import colress.testutil.UiAdvancedStub;
import colress.testutil.UiBeginnerStub;

public class FindCommandTest {
    @Test
    public void startTest() {
        FindCommand findCommand = new FindCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedString = colressUiBeginner.promptKeyword(taskList);
        assertEquals(expectedString, findCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiBeginner_success() {
        FindCommand findCommand = new FindCommand(new String[]{VALID_KEYWORD_ONE}, VALID_KEYWORD_ONE);
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedResult = taskList.retrieveTasks(VALID_KEYWORD_ONE);
        assertEquals(expectedResult, findCommand.execute(colressUiBeginner, taskList));
    }

    @Test
    public void execute_uiAdvancedInvalidFormat_exceptionThrown() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // no arguments -> exception thrown
        FindCommand findCommandNoArguments = new FindCommand();

        assertThrows(InvalidCommandFormatException.class, () ->
                findCommandNoArguments.execute(colressUiAdvanced, taskList));

        // too many arguments -> exception thrown
        FindCommand findCommandTooManyArguments = new FindCommand(new String[]
            {VALID_KEYWORD_ONE, VALID_KEYWORD_ONE});

        assertThrows(InvalidCommandFormatException.class, () ->
                findCommandTooManyArguments.execute(colressUiAdvanced, taskList));
    }


    @Test
    public void execute_uiAdvanced_success() {
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        // valid input, non-empty result
        FindCommand findCommand = new FindCommand(new String[]{VALID_KEYWORD_ONE}, VALID_KEYWORD_ONE);

        String expectedResult = taskList.retrieveTasks(VALID_KEYWORD_ONE);
        assertEquals(expectedResult, findCommand.execute(colressUiAdvanced, taskList));

        // valid input, empty result
        findCommand = new FindCommand(new String[]{VALID_KEYWORD_NONE}, VALID_KEYWORD_NONE);

        expectedResult = MESSAGE_LIST_EMPTY;
        assertEquals(expectedResult, findCommand.execute(colressUiAdvanced, taskList));

        // invalid input
        findCommand = new FindCommand(new String[]{EMPTY_STRING});
        colressUiAdvanced = new UiAdvancedStub(true, false);

        expectedResult = new EmptyInputException().getMessage();
        assertEquals(expectedResult, findCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        FindCommand findCommand = new FindCommand();

        String expectedString = Parser.COMMAND_FIND;
        assertEquals(expectedString, findCommand.toString());
    }

    @Test
    public void equalsTest() {
        FindCommand findCommand = new FindCommand();

        // null values -> returns true
        assertTrue(findCommand.equals(new FindCommand()));

        findCommand = new FindCommand(new String[]{VALID_KEYWORD_ONE}, VALID_KEYWORD_ONE);

        // same values -> returns true
        assertTrue(findCommand.equals(new FindCommand(new String[]{VALID_KEYWORD_ONE}, VALID_KEYWORD_ONE)));

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // null -> returns false
        assertFalse(findCommand.equals(null));

        // different types -> returns false
        assertFalse(findCommand.equals(17));

        // different values -> returns false
        assertFalse(findCommand.equals(new FindCommand(new String[]{VALID_KEYWORD_TWO}, VALID_KEYWORD_TWO)));
    }
}
