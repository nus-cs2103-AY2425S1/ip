package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.util.Storage;
import wolfie.util.Ui;

class DeleteCommandTest {
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
    void testExecute_validIndex() throws IOException, WolfieException {
        tasks.add(new Todo("Test task", false));
        DeleteCommand command = new DeleteCommand("1");
        command.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
    }

    @Test
    void testExecute_invalidIndex() {
        DeleteCommand command = new DeleteCommand("1");
        assertThrows(WolfieException.class, () -> command.execute(tasks, ui, storage));
    }
}
