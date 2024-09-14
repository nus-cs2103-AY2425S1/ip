package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

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
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_nullDate_throwsInvalidCommandException() {
        DeadlineCommand command = new DeadlineCommand("finish assignment");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_missingByKeyword_throwsInvalidCommandException() {
        DeadlineCommand command = new DeadlineCommand("finish assignment tomorrow");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_validInput_addTaskSuccess() throws InvalidCommandException {
        DeadlineCommand command = new DeadlineCommand("submit report /by 2024-08-31 2359");
        String result = command.execute(taskList);

        Deadline d = new Deadline("submit report", LocalDateTime.of(2024, 8, 31, 23, 59));
        String expected = String.format("Got it. I've added this task:\n  %s\nNow you have 1 tasks in the list.\n", d);
        assertEquals(expected, result);
        assertEquals(1, taskList.size());
        assertInstanceOf(Deadline.class, taskList.getTaskList().get(0));
    }

    @Test
    public void execute_invalidDateFormat_addTaskFailed() {
        DeadlineCommand command = new DeadlineCommand("finish assignment /by invalid-date");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }
}
