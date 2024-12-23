package colress.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.Colress;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.parser.Parser;
import colress.storage.Storage;
import colress.tasklist.TaskList;
import colress.testutil.CorrectFormatStorageStub;
import colress.testutil.CorrectFormatTaskListStub;

public class ToggleCommandTest {
    @Test
    public void startTest() {
        ToggleCommand toggleCommand = new ToggleCommand();
        Storage storage = new CorrectFormatStorageStub();
        TaskList taskList = new CorrectFormatTaskListStub();

        // Advanced to Beginner
        Colress colress = new Colress(storage, taskList, false, false);
        UiBeginner uiBeginner = new UiBeginner(colress);

        String expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Beginner");
        assertEquals(expectedResult, toggleCommand.start(uiBeginner, taskList));

        // Beginner to Advanced
        colress = new Colress(storage, taskList, false, true);
        uiBeginner = new UiBeginner(colress);

        expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Advanced");
        assertEquals(expectedResult, toggleCommand.start(uiBeginner, taskList));
    }

    @Test
    public void executeTest() {
        ToggleCommand toggleCommand = new ToggleCommand();
        Storage storage = new CorrectFormatStorageStub();
        TaskList taskList = new CorrectFormatTaskListStub();

        // Advanced to Beginner
        Colress colress = new Colress(storage, taskList, false, false);
        UiAdvanced uiAdvanced = new UiAdvanced(colress);

        String expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Beginner");
        assertEquals(expectedResult, toggleCommand.execute(uiAdvanced, taskList));

        // Beginner to Advanced
        colress = new Colress(storage, taskList, false, true);
        uiAdvanced = new UiAdvanced(colress);

        expectedResult = String.format(ToggleCommand.MESSAGE_SUCCESSFUL_EXECUTION, "Advanced");
        assertEquals(expectedResult, toggleCommand.execute(uiAdvanced, taskList));
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
