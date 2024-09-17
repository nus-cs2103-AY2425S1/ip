package bangmang.command;

import static org.junit.jupiter.api.Assertions.*;

import bangmang.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Todo;

public class ListCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = Mockito.mock(Ui.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    public void testExecuteWithTasks() throws InvalidCommandException {
        Task task = new Todo("Test Todo");
        tasks.add(task);
        ListCommand command = new ListCommand();
        Mockito.when(ui.showAllTasks(tasks.getTasks())).thenReturn("List of tasks");
        String result = command.execute(tasks, ui, storage);
        assertEquals("List of tasks", result);
    }

    @Test
    public void testExecuteNoTasks() throws InvalidCommandException {
        ListCommand command = new ListCommand();
        Mockito.when(ui.showNoTasks()).thenReturn("No tasks found");
        String result = command.execute(tasks, ui, storage);
        assertEquals("No tasks found", result);
    }
}
