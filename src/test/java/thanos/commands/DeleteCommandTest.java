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

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("No task index provided. Please use the correct format: 'delete [task index]'",
                exception.getMessage());
    }

    @Test
    public void execute_validIndex_deleteTaskSuccess() throws InvalidCommandException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        DeleteCommand command = new DeleteCommand("2");
        command.execute(taskList);

        assertEquals(1, taskList.size(), "TaskList should contain 1 task after deletion");
        assertEquals(task1, taskList.getTaskList().get(0), "The remaining task should be Task 1");
    }

    @Test
    public void execute_nonIntegerTaskIndex_throwsInvalidCommandException() {
        DeleteCommand command = new DeleteCommand("one");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid task index. The task index provided is not an integer.",
                exception.getMessage());
    }

    @Test
    public void execute_outOfRangeIndex_throwsInvalidCommandException() {
        Task task1 = new Todo("Task 1");
        taskList.add(task1);
        DeleteCommand command = new DeleteCommand("2");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid task index. The task index provided is out of range.",
                exception.getMessage());
    }
}
