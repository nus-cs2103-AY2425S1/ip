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
import bangmang.tasks.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommandTest {

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
        AddDeadlineCommand command = new AddDeadlineCommand("Test Deadline", "2024-09-17T23:59");
        Mockito.when(ui.showAddedNewTask(Mockito.any(Deadline.class), Mockito.any(TaskList.class)))
                .thenReturn("Added Deadline");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Added Deadline", result);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);
    }

    @Test
    public void testExecuteInvalidDate() {
        AddDeadlineCommand command = new AddDeadlineCommand("Test Deadline", "invalid-date");
        assertThrows(InvalidCommandException.class, () -> command.execute(tasks, ui, storage));
    }
}
