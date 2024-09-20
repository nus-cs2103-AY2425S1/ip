package soju.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import soju.Storage;
import soju.TaskList;
import soju.Ui;
import soju.tasks.Todo;

public class ListCommandTest {

    @Test
    public void execute_tasksPresent_listReturned() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");

        // Add some tasks to the task list
        taskList.addTask(new Todo("read a book"));
        taskList.addTask(new Todo("do homework"));

        // Create a ListCommand
        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are the tasks in your list:\n" + taskList.toString();

        // Execute the command and get the response
        String result = listCommand.execute(taskList, ui, storage);

        // Verify the output matches the expected
        assertEquals(expectedOutput, result);
    }

    @Test
    public void execute_noTasks_emptyListReturned() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");

        // Create a ListCommand with no tasks in the task list
        ListCommand listCommand = new ListCommand("list");
        String expectedOutput = "Here are the tasks in your list:\n" + taskList.toString();

        // Execute the command and get the response
        String result = listCommand.execute(taskList, ui, storage);

        // Verify the output matches the expected
        assertEquals(expectedOutput, result);
    }
}
