package colress.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.Chatbot;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.parser.Parser;
import colress.storage.Storage;
import colress.tasklist.TaskList;
import colress.testutil.ColressStub;
import colress.testutil.CorrectFormatStorageStub;
import colress.testutil.CorrectFormatTaskListStub;
import colress.testutil.UiAdvancedStub;
import colress.testutil.UiBeginnerStub;

public class ToggleCommandTest {
    @Test
    public void startTest() {
        ToggleCommand toggleCommand = new ToggleCommand();

        // Advanced to Beginner
        Chatbot colress = new ColressStub(false, false);
        UiBeginner colressUiBeginner = new UiBeginnerStub(colress);;

        String expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Beginner");
        assertEquals(expectedResult, toggleCommand.start(colressUiBeginner, colress.getTaskList()));

        // Beginner to Advanced
        colress = new ColressStub(false, true);
        colressUiBeginner = new UiBeginnerStub(colress);;

        expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Advanced");
        assertEquals(expectedResult, toggleCommand.start(colressUiBeginner, colress.getTaskList()));
    }

    @Test
    public void executeTest() {
        ToggleCommand toggleCommand = new ToggleCommand();

        // Advanced to Beginner
        Chatbot colress = new ColressStub(false, false);
        UiAdvanced colressUiAdvanced = new UiAdvancedStub(colress);

        String expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Beginner");
        assertEquals(expectedResult, toggleCommand.execute(colressUiAdvanced, colress.getTaskList()));

        // Beginner to Advanced
        colress = new ColressStub(false, true);
        colressUiAdvanced = new UiAdvancedStub(colress);

        expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Advanced");
        assertEquals(expectedResult, toggleCommand.execute(colressUiAdvanced, colress.getTaskList()));
    }

    @Test
    public void toStringTest() {
        ToggleCommand toggleCommand = new ToggleCommand();

        String expectedString = Parser.COMMAND_TOGGLE;
        assertEquals(expectedString, toggleCommand.toString());
    }

    @Test
    public void equalsTest() {
        ToggleCommand toggleCommand = new ToggleCommand();

        // same object -> returns true
        assertTrue(toggleCommand.equals(toggleCommand));

        // different objects -> returns true
        assertTrue(toggleCommand.equals(new ToggleCommand()));

        // null -> returns false
        assertFalse(toggleCommand.equals(null));

        // different types -> returns false
        assertFalse(toggleCommand.equals(17));
    }
}
