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
import bangmang.tasks.Event;

import java.time.LocalDateTime;

public class AddEventCommandTest {

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
    public void testExecuteValidDate() throws InvalidCommandException, InvalidTaskFormatException {
        AddEventCommand command = new AddEventCommand("Test Event", "2024-09-17T10:00", "2024-09-17T12:00");
        Mockito.when(ui.showAddedNewTask(Mockito.any(Event.class), Mockito.any(TaskList.class)))
                .thenReturn("Added Event");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Added Event", result);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Event);
    }

    @Test
    public void testExecuteInvalidDate() {
        AddEventCommand command = new AddEventCommand("Test Event", "invalid-date", "invalid-date");
        assertThrows(InvalidCommandException.class, () -> command.execute(tasks, ui, storage));
    }
}
