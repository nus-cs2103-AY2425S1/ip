package vinegar.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vinegar.storage.Storage;
import vinegar.task.TaskList;
import vinegar.ui.Ui;
import vinegar.VinegarException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the AddEventCommand class.
 * <p>
 * Tests the functionality of adding an event task, including both valid and invalid inputs.
 */
public class AddEventCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the environment before each test case.
     * Initializes the task list, UI, and storage.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Tests adding a valid event task.
     * Ensures that the event is successfully added to the task list and properly formatted.
     *
     * @throws IOException       If there is an I/O error during the test.
     * @throws VinegarException  If an exception occurs during command execution.
     */
    @Test
    public void testExecute_validEvent_success() throws IOException, VinegarException {
        String[] inputParts = {"event play", "play /from 2024-08-31 15:00 /to 2024-08-31 17:00"};
        AddEventCommand command = new AddEventCommand(inputParts);
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("[E][ ] play (from: Aug 31 2024 15:00 to: Aug 31 2024 17:00)", taskList.get(0).toString());
    }

    /**
     * Tests adding an event with invalid date format.
     * Ensures that the appropriate VinegarException is thrown.
     */
    @Test
    public void testExecute_invalidEvent_throwsVinegarException() {
        String[] inputParts = {"event play", "play /from invalidDate /to invalidDate"};
        assertThrows(VinegarException.class, () -> {
            AddEventCommand command = new AddEventCommand(inputParts);
            command.execute(taskList, ui, storage);
        });
    }
}
