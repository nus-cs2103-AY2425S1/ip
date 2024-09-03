package nimbus.command;

import nimbus.ui.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void testDeleteCommandOneTask() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();

        String userInput = "delete 1";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals(0, taskList.getTaskList().size());
    }

    @Test
    public void testDeleteCommandManyTasks() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();
        new AddCommand("deadline Tutorial /by 22/8/2024 1800", taskList).execute();

        String userInput = "delete 1";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals("[D][ ] Tutorial  (by: Aug 22 2024 6:00 pm)",
                taskList.getTaskList().get(0).toString().trim());
    }

    @Test
    public void testDeleteCommandWrongUserInput() {
        TaskList taskList = new TaskList();
        new AddCommand("todo Tutorial", taskList).execute();
        new AddCommand("deadline Tutorial /by 22/8/2024 1800", taskList).execute();

        String userInput = "delete";
        new DeleteCommand(userInput, taskList).execute();
        assertEquals(2,taskList.getTaskList().size());
    }
}
