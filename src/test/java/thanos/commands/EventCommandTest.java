package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.Event;
import thanos.tasks.TaskList;

public class EventCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_emptyArgument_throwsInvalidCommandException() {
        EventCommand command = new EventCommand("");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown");
    }

    @Test
    public void execute_emptyDescription_throwsInvalidCommandException() {
        EventCommand command = new EventCommand("/from 2023-08-30 14:00 /to 2023-08-30 16:00");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown");
    }

    @Test
    public void execute_missingFromKeyword_throwsInvalidCommandException() {
        EventCommand command = new EventCommand("project meeting /to 2023-08-30 16:00");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown");
    }

    @Test
    public void execute_missingToKeyword_throwsInvalidCommandException() {
        EventCommand command = new EventCommand("project meeting /from 2023-08-30 14:00");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown");
    }

    @Test
    public void execute_validInput_addTaskSuccess() throws InvalidCommandException {
        EventCommand command = new EventCommand("project meeting /from 2023-08-30 14:00 /to 2023-08-30 16:00");
        command.execute(taskList);

        assertEquals(1, taskList.size(), "TaskList should contain 1 task after adding an event");
        assertInstanceOf(Event.class, taskList.getTaskList().get(0), "Expected an Event class");
    }

    @Test
    public void execute_invalidDateFormat_addTaskFailed() throws InvalidCommandException {
        EventCommand command = new EventCommand("project meeting /from tuesday /to thursday");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList),
                "Expected InvalidCommandException to be thrown");
    }
}
