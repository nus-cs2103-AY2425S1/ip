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

public class MarkTaskCommandTest {

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
        MarkTaskCommand command = new MarkTaskCommand(1);
        Mockito.when(ui.showMarkedTask(Mockito.any(Task.class))).thenReturn("Task marked");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Task marked", result);
        assertTrue(tasks.get(0).getIsDone());
    }

    @Test
    public void testExecuteInvalidIndex() {
        MarkTaskCommand command = new MarkTaskCommand(999);
        assertThrows(InvalidCommandException.class, () -> command.execute(tasks, ui, storage));
    }
}
