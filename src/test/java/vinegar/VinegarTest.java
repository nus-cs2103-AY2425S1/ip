package vinegar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import vinegar.storage.Storage;
import vinegar.task.TaskList;
import vinegar.ui.Ui;
import vinegar.command.Command;
import vinegar.task.Todo;
import vinegar.task.Deadline;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the main Vinegar application functionality.
 * <p>
 * This class simulates command execution, task storage, and task loading for the Vinegar application.
 */
public class VinegarTest {

    private Vinegar vinegar;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    @TempDir
    Path tempDir; // Temporary directory for testing

    /**
     * Initializes Vinegar and its components before each test.
     */
    @BeforeEach
    public void setUp() {
        String filePath = tempDir.resolve("vinegar.txt").toString();
        vinegar = new Vinegar();
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    /**
     * Tests the execution of the 'todo' command.
     */
    @Test
    public void testRunCommand_AddTodo() throws IOException, VinegarException {
        Command command = Parser.parse("todo read book");
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Todo);
        assertTrue(tasks.get(0).toString().contains("read book"));
    }

    /**
     * Tests the execution of the 'deadline' command.
     */
    @Test
    public void testRunCommand_AddDeadline() throws IOException, VinegarException {
        Command command = Parser.parse("deadline submit assignment /by 2023-12-31 23:59");
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);
        assertTrue(tasks.get(0).toString().contains("submit assignment"));
        assertTrue(tasks.get(0).toString().contains("Dec 31 2023 23:59"));
    }

    /**
     * Tests handling of an invalid command input.
     */
    @Test
    public void testRunCommand_InvalidCommand() {
        assertThrows(VinegarException.class, () -> {
            Command command = Parser.parse("invalid command");
            command.execute(tasks, ui, storage);
        });
    }

    /**
     * Tests saving tasks to a file.
     */
    @Test
    public void testSaveTasksToFile() throws IOException {
        tasks.addTask(new Todo("read book"));
        storage.save(tasks.getTasks());

        assertTrue(tempDir.resolve("vinegar.txt").toFile().exists());
    }

    /**
     * Tests loading tasks from a file.
     */
    @Test
    public void testLoadTasksFromFile() throws IOException {
        tasks.addTask(new Todo("read book"));
        storage.save(tasks.getTasks());

        TaskList loadedTasks = new TaskList(storage.load());

        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0).toString().contains("read book"));
    }
}
