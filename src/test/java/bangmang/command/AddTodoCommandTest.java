package bangmang.command;

import static org.junit.jupiter.api.Assertions.*;

import bangmang.exception.InvalidTaskFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Todo;

public class AddTodoCommandTest {

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
        AddTodoCommand command = new AddTodoCommand("Test Todo");
        Mockito.when(ui.showAddedNewTask(Mockito.any(Todo.class), Mockito.any(TaskList.class)))
                .thenReturn("Added Todo");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Added Todo", result);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Todo);
    }
}
