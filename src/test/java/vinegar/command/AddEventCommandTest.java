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


public class AddEventCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("data/test_storage.txt");
    }

    @Test
    public void testExecute_validEvent_success() throws IOException, VinegarException {
        String[] inputParts = {"event play", "play /from 2024-08-31 15:00 /to 2024-08-31 17:00"};
        AddEventCommand command = new AddEventCommand(inputParts);
        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("[E][ ] play (from: Aug 31 2024 15:00 to: Aug 31 2024 17:00)", taskList.get(0).toString());
    }

    @Test
    public void testExecute_invalidEvent_throwsVinegarException() {
        String[] inputParts = {"event play", "play /from invalidDate /to invalidDate"};
        assertThrows(VinegarException.class, () -> {
            AddEventCommand command = new AddEventCommand(inputParts);
            command.execute(taskList, ui, storage);
        });
    }
}
