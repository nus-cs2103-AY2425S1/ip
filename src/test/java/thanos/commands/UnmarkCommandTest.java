package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;

public class UnmarkCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_noIndexProvided_throwsInvalidCommandException() {
        UnmarkCommand command = new UnmarkCommand("");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("No task index provided. Please use the correct format: 'unmark [task index]'",
                exception.getMessage());
    }

    @Test
    public void execute_multipleArgumentsProvided_throwsInvalidCommandException() {
        UnmarkCommand command = new UnmarkCommand("1 2");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid input format. Please use the correct format: 'unmark [task index]'",
                exception.getMessage());
    }

    @Test
    public void execute_validTaskIndex_unmarkTaskSuccess() throws InvalidCommandException {
        Todo todo = new Todo("read book");
        taskList.add(todo);
        taskList.mark(0);
        UnmarkCommand command = new UnmarkCommand("1");
        command.execute(taskList);

        assertEquals(
                "[T][ ] read book",
                taskList.getTaskList().get(0).toString()
        );
    }

    @Test
    public void execute_nonIntegerTaskIndex_throwsInvalidCommandException() {
        UnmarkCommand command = new UnmarkCommand("one");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid task index. The task index provided is not an integer.", exception.getMessage());
    }

    @Test
    public void execute_outOfBoundsTaskIndex_throwsInvalidCommandException() {
        taskList.add(new Todo("read book"));
        UnmarkCommand command = new UnmarkCommand("2");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid task index. The task index provided is out of range.", exception.getMessage());
    }
}
