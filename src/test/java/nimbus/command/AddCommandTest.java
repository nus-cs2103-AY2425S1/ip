package nimbus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nimbus.exception.WrongInputException;
import nimbus.ui.TaskList;

/**
 * Tests method in add Command
 */
public class AddCommandTest {

    /**
     * Tests if todoTask can be added correctly
     */
    @Test
    public void testAddCommand_todoTask() {
        String userInput = "todo Tutorial";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals("[T][ ] Tutorial",
                taskList.getTaskList().get(0).toString().trim());
    }

    /**
     * Tests if deadlineTask can be added correctly
     */
    @Test
    public void testAddCommand_deadlineTask() {
        String userInput = "deadline Tutorial /by 22/8/2024 1200";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals("[D][ ] Tutorial (by: Aug 22 2024 12:00 pm)",
                taskList.getTaskList().get(0).toString().trim());
    }

    /**
     * Tests if eventTask can be added correctly
     */
    @Test
    public void testAddCommand_eventTask() {
        String userInput = "event appointment /from 22/8/2024 1200 /to 22/8/2024 1400";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals("[E][ ] appointment (from: Aug 22 2024 12:00 pm to: Aug 22 2024 2:00 pm)",
                taskList.getTaskList().get(0).toString().trim());
    }

    /**
     * Tests if exception thrown if wrong description
     */
    @Test
    public void testAddCommand_wrongDescription() {
        String userInput = "random";
        TaskList taskList = new TaskList();

        WrongInputException exception = assertThrows(WrongInputException.class, () -> {
            new AddCommand(userInput, taskList).execute();
        });

        String expectedMessage = "Sorry Nimbus don't understand what you are saying QwQ \n"
                + "Try using todo, deadline or event!";
        assertTrue(exception.getMessage().contains(expectedMessage));

        assertEquals(0, taskList.getTaskList().size());
    }
}
