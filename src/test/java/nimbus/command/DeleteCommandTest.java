package nimbus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nimbus.ui.TaskList;

/**
 * Checks if methods in DeleteCommand is working
 */
public class DeleteCommandTest {
    /**
     * Tests if one task can be deleted
     */
    @Test
    public void testDeleteCommand_oneTask() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();

        String userInput = "delete 1";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals(0, taskList.getTaskList().size());
    }

    /**
     * Tests if multiple tasks can be deleted
     */
    @Test
    public void testDeleteCommand_manyTasks() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();
        new AddCommand("deadline Tutorial /by 22/8/2024 1800", taskList).execute();

        String userInput = "delete 1";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals("[D][ ] Tutorial  (by: Aug 22 2024 6:00 pm)",
                taskList.getTaskList().get(0).toString().trim());
    }

    /**
     * Tests if delete command can check if it is the wrong input
     */
    @Test
    public void testDeleteCommand_wrongUserInput() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();
        new AddCommand("deadline Tutorial /by 22/8/2024 1800", taskList).execute();

        String userInput = "delete";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals(2, taskList.getTaskList().size());
    }

    /**
     * Tests if delete command can check if index given is out of range
     */
    @Test
    public void testDeleteCommand_outOfRange() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();
        new AddCommand("deadline Tutorial /by 22/8/2024 1800", taskList).execute();

        String userInput = "delete 3";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals(2, taskList.getTaskList().size());
    }
}
