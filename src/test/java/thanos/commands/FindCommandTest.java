package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;

public class FindCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_emptyArgument_throwsInvalidCommandException() {
        FindCommand command = new FindCommand("");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList), "No date provided.");
    }

    @Test
    public void execute_validKeyword_returnsOneTask() throws InvalidCommandException {
        Task task1 = new Todo("read book");
        Task task2 = new Todo("write code");
        taskList.add(task1);
        taskList.add(task2);
        FindCommand command = new FindCommand("read");
        String result = command.execute(taskList);

        String expected = String.format("Here are the matching tasks in your list:\n1.%s\n", task1);
        assertEquals(expected, result);
    }

    @Test
    public void execute_validKeyword_returnsMultipleTasks() throws InvalidCommandException {
        Task task1 = new Todo("read book");
        Task task2 = new Todo("write code");
        Task task3 = new Todo("read newspaper");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        FindCommand command = new FindCommand("read");
        String result = command.execute(taskList);

        String expected = String.format("Here are the matching tasks in your list:\n1.%s\n2.%s\n", task1, task3);
        assertEquals(expected, result);
    }

    @Test
    public void execute_validKeyword_returnsNoTasks() throws InvalidCommandException {
        Task task1 = new Todo("read book");
        Task task2 = new Todo("write code");
        taskList.add(task1);
        taskList.add(task2);
        FindCommand command = new FindCommand("eat lunch");
        String result = command.execute(taskList);

        String expected = "No tasks found\n";
        assertEquals(expected, result);
    }
}
