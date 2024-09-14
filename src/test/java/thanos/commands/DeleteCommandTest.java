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

public class DeleteCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_noIndexProvided_throwsInvalidCommandException() {
        DeleteCommand command = new DeleteCommand("");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_validIndex_deleteTaskSuccess() throws InvalidCommandException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        DeleteCommand command = new DeleteCommand("2");
        String result = command.execute(taskList);

        String expected = String.format("Noted. I've removed these task(s):\n1.%s\nNow you have 1 tasks in the list.\n",
                task2);
        assertEquals(expected, result);
        assertEquals(1, taskList.size(), "TaskList should contain 1 task after deletion");
        assertEquals(task1, taskList.getTaskList().get(0), "The remaining task should be Task 1");
    }

    @Test
    public void execute_nonIntegerTaskIndex_throwsInvalidCommandException() {
        DeleteCommand command = new DeleteCommand("one");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_outOfRangeIndex_throwsInvalidCommandException() {
        Task task1 = new Todo("Task 1");
        taskList.add(task1);
        DeleteCommand command = new DeleteCommand("2");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_multipleIndex_success() throws InvalidCommandException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        Task task3 = new Todo("Task 3");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        DeleteCommand command = new DeleteCommand("1 3");
        String result = command.execute(taskList);

        String expected = String.format("""
                Noted. I've removed these task(s):
                1.%s
                2.%s
                Now you have 1 tasks in the list.
                """, task3, task1);
        assertEquals(expected, result);
        assertEquals(1, taskList.size(), "TaskList should contain 1 task after deletion");
    }

    @Test
    public void execute_duplicateIndex_throwsInvalidCommandException() {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        DeleteCommand command = new DeleteCommand("1 1");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }
}
