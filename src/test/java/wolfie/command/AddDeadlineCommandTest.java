package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

class AddDeadlineCommandTest {
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
    void testExecute_addDeadline() throws IOException, WolfieException {
        String arguments = "Test task /by 2023-10-01 1000";
        AddDeadlineCommand command = new AddDeadlineCommand(arguments);
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("Test task", tasks.get(0).getDescription());
    }
}
