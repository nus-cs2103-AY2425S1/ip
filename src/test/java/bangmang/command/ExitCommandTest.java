package bangmang.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

public class ExitCommandTest {

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
    public void testExecute() throws InvalidCommandException {
        ExitCommand command = new ExitCommand();
        Mockito.when(ui.showExit()).thenReturn("Goodbye!");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Goodbye!", result);
    }
}
