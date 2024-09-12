package tira;

import org.junit.jupiter.api.Test;

import tira.task.Task;
import tira.task.TaskList;
import tira.task.ToDo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {
    @Test
    public void showTaskListTest() throws TiraException{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addToDo("Test", "todo Test".split(" "));
        ui.showTaskList(taskList);
        String expectedString = "Miao! Got it. I've added this task to my cat brain:\n"
                + "[T][ ] Test\n"
                + "Now you have 1 task(s) in the list!\n"
                + "HERE ARE THE CURRENT TASKS:\n"
                + "1. [T][ ] Test";
        assertEquals(expectedString, output.toString().trim());
    }

    @Test
    public void showMarkTaskTest() throws TiraException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test");
        taskList.getTasks().add(task);
        task.unmarkStatus();
        ui.showUnmarkTask(task);
        String expectedOutput = "MRAWWW! Don't forget to return to this task:\n" +
                "[T][ ] Test";
        assertEquals(expectedOutput, output.toString().trim());

    }
}
