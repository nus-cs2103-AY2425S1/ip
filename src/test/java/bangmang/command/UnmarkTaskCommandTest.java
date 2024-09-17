package bangmang.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;
import bangmang.tasks.Todo;

public class UnmarkTaskCommandTest {

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
    public void testExecute() throws InvalidCommandException, InvalidTaskFormatException {
        Task task = new Todo("Test Task");
        tasks.add(task);
        tasks.markTask(0);
        UnmarkTaskCommand command = new UnmarkTaskCommand(1);
        Mockito.when(ui.showUnmarkedTask(Mockito.any(Task.class))).thenReturn("Task unmarked");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Task unmarked", result);
        assertFalse(tasks.get(0).getIsDone());
    }

    @Test
    public void testExecuteInvalidIndex() {
        UnmarkTaskCommand command = new UnmarkTaskCommand(999);
        assertThrows(InvalidCommandException.class, () -> command.execute(tasks, ui, storage));
    }
}
