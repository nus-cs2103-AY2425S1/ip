package noosy.commands;

import noosy.exception.NoosyException;
import noosy.stubs.StorageStub;
import noosy.stubs.UiStub;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {

    @Test
    public void testExecute_marksTaskSuccessfully() throws NoosyException {
        // Set up a TaskList with one task
        Task todo = new Todo("Test task");
        TaskList tasks = new TaskList();
        tasks.add(todo);

        // Set up stubs
        UiStub uiStub = new UiStub();
        StorageStub storageStub = new StorageStub();

        // Create a MarkCommand for the first task (index 0)
        MarkCommand markCommand = new MarkCommand(0);

        // Execute the MarkCommand
        markCommand.execute(tasks, uiStub, storageStub);

        // Verify that the task was marked as done
        assertTrue(tasks.get(0).isDone());

        // Optionally, verify that the task remains in the task list
        assertEquals(1, tasks.size());
    }
}