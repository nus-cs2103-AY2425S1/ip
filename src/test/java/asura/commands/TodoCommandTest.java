package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        try {
            storage = new Storage("data/asura.txt");
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    @Test
    public void testExecute() throws AsuraException {
        String taskString = "read book";
        TodoCommand command = new TodoCommand(taskString);
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("read book", taskList.get(0).getDescription());
    }

    @Test
    public void testIsExit() {
        TodoCommand command = new TodoCommand("read book");
        assertFalse(command.isExit());
    }
}
