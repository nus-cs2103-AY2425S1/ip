package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.Deadline;
import thanos.tasks.TaskList;

public class DeadlineCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_emptyArgument_throwsInvalidCommandException() {
        DeadlineCommand command = new DeadlineCommand("");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'",
                exception.getMessage());
    }

    @Test
    public void execute_nullDate_throwsInvalidCommandException() {
        DeadlineCommand command = new DeadlineCommand("finish assignment");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'",
                exception.getMessage());
    }

    @Test
    public void execute_missingByKeyword_throwsInvalidCommandException() {
        DeadlineCommand command = new DeadlineCommand("finish assignment tomorrow");

        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid input format. Please use the correct format: 'deadline [task] /by [due date]'",
                exception.getMessage());
    }

    @Test
    public void execute_validInput_addTaskSuccess() throws InvalidCommandException {
        DeadlineCommand command = new DeadlineCommand("submit report /by 2024-08-31 2359");
        command.execute(taskList);

        assertEquals(1, taskList.size(), "TaskList should contain 1 task after adding a deadline");
        assertInstanceOf(Deadline.class, taskList.getTaskList().get(0), "Expected a Deadline class");
    }

    @Test
    public void execute_invalidDateFormat_addTaskFailed() throws InvalidCommandException {
        DeadlineCommand command = new DeadlineCommand("finish assignment /by invalid-date");
        InvalidCommandException exception = assertThrows(
                InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown"
        );
        assertEquals("Invalid datetime format: invalid-date",
                exception.getMessage());
    }
}
