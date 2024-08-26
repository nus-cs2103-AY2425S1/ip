// src/test/java/wolfie/command/MarkCommandTest.java
package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.util.Storage;
import wolfie.util.Ui;

class MarkCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
    }

    @Test
    void testExecute_validIndex() throws IOException, WolfieException {
        tasks.add(new Todo("Test task", false));
        MarkCommand command = new MarkCommand("1");
        command.execute(tasks, ui, storage);

        Task task = tasks.get(0);
        assertTrue(task.getIsDone());
    }

    @Test
    void testExecute_invalidIndex() {
        MarkCommand command = new MarkCommand("1");
        assertThrows(WolfieException.class, () -> command.execute(tasks, ui, storage));
    }
}
