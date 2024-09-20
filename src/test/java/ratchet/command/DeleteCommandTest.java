package ratchet.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ratchet.exception.InvalidCommandArgumentException;
import ratchet.storage.Storage;
import ratchet.task.TaskList;
import ratchet.task.TodoTask;
import ratchet.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        try {
            new DeleteCommand(new Integer[]{1}).execute(new Storage(), new TaskList(), new Ui());
            fail();
        } catch (InvalidCommandArgumentException e) {
            assertEquals("Invalid command argument: Please enter a valid task index!",
                    e.getMessage());
        }
    }

    @Test
    public void execute_validTaskIndex_success() throws InvalidCommandArgumentException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("test"));
        new DeleteCommand(new Integer[]{0}).execute(new Storage(), tasks, new Ui());
        assertEquals(0, tasks.size());
        assertEquals("You have no tasks currently", tasks.toString());
    }

    @Test
    public void execute_validTaskIndexes_success() throws InvalidCommandArgumentException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("first test"));
        tasks.addTask(new TodoTask("second test"));
        new DeleteCommand(new Integer[]{1, 0}).execute(new Storage(), tasks, new Ui());
        assertEquals(0, tasks.size());
        assertEquals("You have no tasks currently", tasks.toString());
    }
}
