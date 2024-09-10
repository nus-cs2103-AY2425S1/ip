package monique.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monique.exception.MarkException;
import monique.storage.Storage;
import monique.task.Task;
import monique.task.ToDo;
import monique.tasklist.TaskList;
import monique.ui.Ui;


public class MarkCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        tasks = new TaskList(new ArrayList<>());
        ui = new Ui(); // Assuming Ui can be instantiated directly
        storage = new Storage("data/tasks.txt"); // Assuming a valid path
    }

    @Test
    public void isActive_basicTestCase_true() {
        assertTrue(new MarkCommand(1).isActive());
    }

    @Test
    public void execute_validTaskNumber_marksTask() throws MarkException {
        Task task = new ToDo("Test Task");
        tasks.addTask(task);
        MarkCommand command = new MarkCommand(0);
        command.execute(tasks, ui, storage);
        assertTrue(tasks.getTask(0).isComplete());
    }

    @Test
    public void execute_invalidTaskNumber_throwsMarkException() {
        MarkCommand command = new MarkCommand(10);
        assertThrows(MarkException.class, () -> command.execute(tasks, ui, storage));
    }

    @Test
    public void execute_negativeTaskNumber_throwsMarkException() {
        MarkCommand command = new MarkCommand(-1);
        assertThrows(MarkException.class, () -> command.execute(tasks, ui, storage));
    }
}
