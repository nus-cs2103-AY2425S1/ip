package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

class AddEventCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/testTasks.txt");
    }

    @Test
    void testExecute_addEvent() throws IOException, WolfieException {
        String arguments = "Test task /from 2023-10-01 1000 /to 2023-10-01 1200";
        AddEventCommand command = new AddEventCommand(arguments);
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("Test task", tasks.get(0).getDescription());
    }
}
