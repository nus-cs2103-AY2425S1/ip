package tira;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tira.task.Task;
import tira.task.TaskList;
import tira.task.ToDo;



public class UiTest {
    @Test
    public void showTaskListTest() throws TiraException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addToDo("Test", "todo Test".split(" ")); //added ToDo test
        ui.showTaskList(taskList);
        String expectedString = "HERE ARE THE CURRENT TASKS:\n"
                + "1. [T][ ] Test\n";
        assertEquals(expectedString, ui.showTaskList(taskList));
    }

    @Test
    public void markTaskTest() throws TiraException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test");
        taskList.getTasks().add(task);
        task.unmarkStatus();
        ui.showUnmarkTask(task);
        String expectedOutput = "MRAWWW! Don't forget to return to this task:\n"
                + "[T][ ] Test\n";
        assertEquals(expectedOutput, ui.getOutMessage());

    }
}
