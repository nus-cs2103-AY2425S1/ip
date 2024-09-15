package taskon.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskon.common.Messages.MESSAGE_INVALID_EDIT;
import static taskon.common.Messages.MESSAGE_SUCCESSFUL_EDIT;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.task.Todo;
import taskon.ui.Ui;

public class EditCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("Read a book"));
        ui = new Ui();
        storage = new Storage("testStorage.txt"); // Using a mock storage system for tests
    }

    @Test
    public void execute_validEditCommand_success() {
        EditCommand command = new EditCommand(0, "description", "Read two books");
        String result = command.execute(taskList, ui, storage);
        assertTrue(taskList.getTask(0).getDescription().equals("Read two books"));
        assertEquals(MESSAGE_SUCCESSFUL_EDIT + "[T][ ] Read two books", result);
    }

    @Test
    public void execute_invalidIndex_outOfBounds() {
        EditCommand command = new EditCommand(10, "description", "Invalid edit");
        String result = command.execute(taskList, ui, storage);
        assertEquals("You only have 1 tasks!\n", result);
    }

    @Test
    public void execute_invalidFieldToEdit_returnsError() {
        EditCommand command = new EditCommand(0, "unknownField", "Invalid edit");
        String result = command.execute(taskList, ui, storage);
        assertEquals(MESSAGE_INVALID_EDIT, result);
    }
}
