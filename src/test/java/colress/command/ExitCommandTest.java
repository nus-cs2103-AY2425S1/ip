package colress.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.UiAdvanced;
import colress.UiBeginner;
import colress.parser.Parser;
import colress.tasklist.ColressTaskList;
import colress.tasklist.TaskList;
import colress.testutil.UiAdvancedStub;
import colress.testutil.UiBeginnerStub;

public class ExitCommandTest {
    @Test
    public void startTest() {
        ExitCommand exitCommand = new ExitCommand();
        UiBeginner colressUiBeginner = new UiBeginnerStub();
        TaskList taskList = new ColressTaskList();

        String expectedResult = ExitCommand.MESSAGE_SUCCESSFUL_EXECUTION;
        assertEquals(expectedResult, exitCommand.start(colressUiBeginner, taskList));
    }

    @Test
    public void executeTest() {
        ExitCommand exitCommand = new ExitCommand();
        UiAdvanced colressUiAdvanced = new UiAdvancedStub();
        TaskList taskList = new ColressTaskList();

        String expectedResult = ExitCommand.MESSAGE_SUCCESSFUL_EXECUTION;
        assertEquals(expectedResult, exitCommand.execute(colressUiAdvanced, taskList));
    }

    @Test
    public void toStringTest() {
        ExitCommand exitCommand = new ExitCommand();

        String expectedString = Parser.COMMAND_EXIT;
        assertEquals(expectedString, exitCommand.toString());
    }

    @Test
    public void equalsTest() {
        ExitCommand exitCommand = new ExitCommand();

        // same object -> returns true
        assertTrue(exitCommand.equals(exitCommand));

        // different objects -> returns true
        assertTrue(exitCommand.equals(new ExitCommand()));

        // null -> returns false
        assertFalse(exitCommand.equals(null));

        // different types -> returns false
        assertFalse(exitCommand.equals(17));
    }
}
