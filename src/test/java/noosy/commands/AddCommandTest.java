package noosy.commands;

import noosy.exception.NoosyException;
import noosy.stubs.StorageStub;
import noosy.stubs.UiStub;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    public void testExecute_addsTodoSuccessfully() throws NoosyException {
        // Set up the task, task list, and stubs
        Task todo = new Todo("Test task");
        TaskList tasks = new TaskList();
        UiStub uiStub = new UiStub();
        StorageStub storageStub = new StorageStub();

        // Create an AddCommand with the todo
        AddCommand addCommand = new AddCommand(todo);

        // Execute the AddCommand
        addCommand.execute(tasks, uiStub, storageStub);

        // Verify that the task was added to the task list
        assertEquals(1, tasks.size());
        assertEquals(todo, tasks.get(0));
    }
}