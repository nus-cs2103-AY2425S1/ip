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

public class VinegarTest {

    private Vinegar vinegar;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    @TempDir
    Path tempDir; // Temporary directory for testing

    @BeforeEach
    public void setUp() {
        String filePath = tempDir.resolve("vinegar.txt").toString();
        vinegar = new Vinegar(filePath);
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    @Test
    public void testRunCommand_AddTodo() throws IOException, VinegarException {
        // Simulate adding a Todo task
        Command command = Parser.parse("todo read book");
        command.execute(tasks, ui, storage);

        // Verify that the task list size increased
        assertEquals(1, tasks.size());

        // Verify that the task added is of type Todo and the correct description is returned
        assertTrue(tasks.get(0) instanceof Todo);
        assertTrue(tasks.get(0).toString().contains("read book"));
    }

    @Test
    public void testRunCommand_AddDeadline() throws IOException, VinegarException {
        // Simulate adding a Deadline task
        Command command = Parser.parse("deadline submit assignment /by 2023-12-31 23:59");
        command.execute(tasks, ui, storage);

        // Verify that the task list size increased
        assertEquals(1, tasks.size());

        // Verify that the task added is of type Deadline and the correct deadline is shown in toString
        assertTrue(tasks.get(0) instanceof Deadline);
        assertTrue(tasks.get(0).toString().contains("submit assignment"));
        assertTrue(tasks.get(0).toString().contains("Dec 31 2023 23:59"));
    }

    @Test
    public void testRunCommand_InvalidCommand() {
        // Simulate an invalid command input
        assertThrows(VinegarException.class, () -> {
            Command command = Parser.parse("invalid command");
            command.execute(tasks, ui, storage);
        });
    }

    @Test
    public void testSaveTasksToFile() throws IOException {
        // Simulate saving tasks to a file
        tasks.addTask(new Todo("read book"));
        storage.save(tasks.getTasks());

        // Verify that the file was created
        assertTrue(tempDir.resolve("vinegar.txt").toFile().exists());
    }

    @Test
    public void testLoadTasksFromFile() throws IOException {
        // Simulate saving and then loading tasks from a file
        tasks.addTask(new Todo("read book"));
        storage.save(tasks.getTasks());

        TaskList loadedTasks = new TaskList(storage.load());

        // Verify that the task was correctly loaded
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0).toString().contains("read book"));
    }
}
