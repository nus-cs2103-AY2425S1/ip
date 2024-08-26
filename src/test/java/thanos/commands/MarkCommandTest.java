package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;
import thanos.ui.Ui;

public class MarkCommandTest {
    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
        ui = new Ui();
    }

    @Test
    public void execute_noIndexProvided_throwsInvalidCommandException() {
        MarkCommand command = new MarkCommand("");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class,
                () -> command.execute(taskList, ui),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("No task index provided. Please use the correct format: 'mark [task index]'",
                exception.getMessage());
    }

    @Test
    public void execute_multipleArgumentsProvided_throwsInvalidCommandException() {
        MarkCommand command = new MarkCommand("1 2");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class,
                () -> command.execute(taskList, ui),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid input format. Please use the correct format: 'mark [task index]'",
                exception.getMessage());
    }

    @Test
    public void execute_validTaskIndex_markTaskSuccess() throws InvalidCommandException {
        taskList.add(new Todo("read book"));
        MarkCommand command = new MarkCommand("1");
        command.execute(taskList, ui);

        assertEquals(
                "[T][X] read book",
                taskList.getTaskList().get(0).toString()
        );
    }

    @Test
    public void execute_nonIntegerTaskIndex_throwsInvalidCommandException() {
        MarkCommand command = new MarkCommand("one");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class,
                () -> command.execute(taskList, ui),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid task index. The task index provided is not an integer.", exception.getMessage());
    }

    @Test
    public void execute_outOfRangeTaskIndex_throwsInvalidCommandException() {
        taskList.add(new Todo("read book"));
        MarkCommand command = new MarkCommand("2");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class,
                () -> command.execute(taskList, ui),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid task index. The task index provided is out of range.", exception.getMessage());
    }
}
