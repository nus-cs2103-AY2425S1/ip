package colress.command;

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

public class ListCommandTest {
    @Test
    public void startTest() {
        ListCommand listCommand = new ListCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = colressUiBeginner.getColress().getTaskList();

        String expectedResult = taskList.retrieveTasks();
        assertEquals(expectedResult, listCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void executeTest() {
        ListCommand listCommand = new ListCommand();
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = colressUiAdvanced.getColress().getTaskList();

        String expectedResult = taskList.retrieveTasks();
        assertEquals(expectedResult, listCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        ListCommand listCommand = new ListCommand();

        String expectedString = Parser.COMMAND_LIST;
        assertEquals(expectedString, listCommand.toString());
    }

    @Test
    public void equalsTest() {
        ListCommand listCommand = new ListCommand();

        // same object -> returns true
        assertTrue(listCommand.equals(listCommand));

        // different objects -> returns true
        assertTrue(listCommand.equals(new ListCommand()));

        // null -> returns false
        assertFalse(listCommand.equals(null));

        // different types -> returns false
        assertFalse(listCommand.equals(17));
    }
}
