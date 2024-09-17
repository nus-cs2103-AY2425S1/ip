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

public class DeleteTaskCommandTest {

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
        DeleteTaskCommand command = new DeleteTaskCommand(1);
        Mockito.when(ui.showDeletedTask(Mockito.any(Task.class), Mockito.any(TaskList.class)))
                .thenReturn("Deleted Task");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Deleted Task", result);
        assertEquals(0, tasks.size());
    }

    @Test
    public void testExecuteInvalidIndex() {
        DeleteTaskCommand command = new DeleteTaskCommand(999);
        assertThrows(InvalidCommandException.class, () -> command.execute(tasks, ui, storage));
    }
}
