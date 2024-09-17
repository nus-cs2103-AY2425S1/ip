package bangmang.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Task;
import bangmang.tasks.Todo;

import java.util.ArrayList;

public class FindCommandTest {

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
        Task task1 = new Todo("Find me");
        Task task2 = new Todo("Don't find me");
        tasks.add(task1);
        tasks.add(task2);
        FindCommand command = new FindCommand("Find");
        ArrayList<Task> results = new ArrayList<>();
        results.add(task1);
        Mockito.when(ui.showSearchResults(results)).thenReturn("Search Results");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Search Results", result);
    }

    @Test
    public void testExecuteNoMatch() throws InvalidCommandException {
        Task task = new Todo("No match");
        tasks.add(task);
        FindCommand command = new FindCommand("Not found");
        Mockito.when(ui.showSearchResults(new ArrayList<>())).thenReturn("No matches found");
        String result = command.execute(tasks, ui, storage);
        assertEquals("No matches found", result);
    }
}
