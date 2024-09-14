package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.Deadline;
import thanos.tasks.TaskList;

public class DateCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_emptyArgument_throwsInvalidCommandException() {
        DateCommand command = new DateCommand("");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList), "No date provided.");
    }

    @Test
    public void execute_invalidDateFormat_throwsInvalidCommandException() {
        DateCommand command = new DateCommand("invalid-date");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_validDateWithNoTasks_returnsEmptyList() throws InvalidCommandException {
        taskList.add(new Deadline("submit report", LocalDateTime.of(2024, 12, 31, 18, 0)));
        DateCommand command = new DateCommand("2023-09-13 12:00");
        String result = command.execute(taskList);
        assertEquals("No tasks found\n", result);
    }

    @Test
    public void execute_validDateWithTasks_returnsTaskList() throws InvalidCommandException {
        Deadline deadline = new Deadline("submit report", LocalDateTime.of(2024, 12, 31, 18, 0));
        taskList.add(deadline);
        DateCommand command = new DateCommand("2024-12-31 18:00");
        String result = command.execute(taskList);
        String expected = String.format("Here are the tasks on: Dec 31 2024 18:00\n1.%s\n", deadline);
        assertEquals(expected, result);
    }
}
