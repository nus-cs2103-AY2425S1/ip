package sage.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sage.List.TaskList;
import sage.SageException;
import sage.Storage;
import sage.Task.ToDoTask;
import sage.Ui;

public class DeleteCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
        tasks.addTask(new ToDoTask("Task 1"));
        tasks.addTask(new ToDoTask("Task 2"));
    }

    @Test
    public void taskDeletion_validIndex_success() throws SageException {
        DeleteCommand deleteCommand = new DeleteCommand("1");
        deleteCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getSize(), "The task list size should be reduced to 1.");
        assertEquals("[T][ ] Task 2", tasks.getTasks().get(0).toString(), "The remaining task should be 'Task 2'.");
    }

    @Test
    public void taskDeletion_nonNumericIndex_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("a");

        SageException exception = assertThrows(SageException.class, () -> {
            deleteCommand.execute(tasks, ui, storage);
        }, "Exception is thrown.");

        assertEquals("Invalid delete command. Index must be a number.", exception.getMessage(), "Exception message appear.");
    }

    @Test
    public void taskDeletion_notWithinRangeIndex_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("3");

        SageException exception = assertThrows(SageException.class, () -> {
            deleteCommand.execute(tasks, ui, storage);
        }, "Exception is thrown.");

        assertEquals("Invalid task index.", exception.getMessage(), "Exception message appear.");
    }

}
