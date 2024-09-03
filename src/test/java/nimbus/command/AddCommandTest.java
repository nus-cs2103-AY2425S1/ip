package nimbus.command;


import nimbus.ui.TaskList;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    @Test
    public void testAddCommandTodoTask() {
        String userInput = "todo Tutorial";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals("[T][ ] Tutorial",
                taskList.getTaskList().get(0).toString().trim());
    }

    @Test
    public void testAddCommandDeadlineTask() {
        String userInput = "deadline Tutorial /by 22/8/2024 1200";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals("[D][ ] Tutorial  (by: Aug 22 2024 12:00 pm)",
                taskList.getTaskList().get(0).toString().trim());
    }

    @Test
    public void testAddCommandEventTask() {
        String userInput = "event appointment /from 22/8/2024 1200 /to 22/8/2024 1400";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals("[E][ ] appointment (from: Aug 22 2024 12:00 pm to: Aug 22 2024 2:00 pm)",
                taskList.getTaskList().get(0).toString().trim());
    }

    @Test
    public void testAddCommandWrongDescription() {
        String userInput = "random";
        TaskList taskList = new TaskList();
        new AddCommand(userInput, taskList).execute();
        assertEquals(0, taskList.getTaskList().size());
    }
}
