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

class UnmarkCommandTest {
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
        tasks.add(new Todo("Test task", true));
        UnmarkCommand command = new UnmarkCommand("1");
        command.execute(tasks, ui, storage);

        assertEquals(false, tasks.get(0).getIsDone());
    }

    @Test
    void testExecute_invalidIndex() {
        UnmarkCommand command = new UnmarkCommand("1");
        assertThrows(WolfieException.class, () -> command.execute(tasks, ui, storage));
    }
}
