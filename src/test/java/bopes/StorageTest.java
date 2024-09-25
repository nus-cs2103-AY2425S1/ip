package bopes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bopes.exception.BopesException;
import bopes.task.Task;
import bopes.task.TaskList;
import bopes.task.ToDo;

/**
 * Unit tests for the Storage class, which handles saving and loading tasks.
 */
public class StorageTest {

    private static final String TEST_FILE_PATH = "test_data/tasks.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Sets up the test environment by initializing the Storage object and cleaning up any existing test files.
     * 
     * @throws Exception if an error occurs during setup.
     */
    @BeforeEach
    public void setUp() throws Exception {
        storage = new Storage(TEST_FILE_PATH);
        tasks = new TaskList(new ArrayList<>());

        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
    }

    /**
     * Cleans up the test environment by deleting the test file and its directory after each test.
     * 
     * @throws Exception if an error occurs during cleanup.
     */
    @AfterEach
    public void tearDown() throws Exception {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        File directory = new File(file.getParent());
        if (directory.exists()) {
            directory.delete();
        }
    }

    /**
     * Tests loading tasks when the file does not exist.
     * 
     * @throws BopesException if an error occurs while loading tasks.
     */
    @Test
    public void testLoadTasksNoFile() throws BopesException {
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertTrue(loadedTasks.isEmpty(), "Tasks should be empty if no file exists");
    }

    /**
     * Tests saving and loading tasks to ensure data consistency.
     * 
     * @throws BopesException if an error occurs during saving or loading tasks.
     */
    @Test
    public void testSaveAndLoadTasks() throws BopesException {
        tasks.addTask(new ToDo("Task 1", false));
        tasks.addTask(new ToDo("Task 2", true));

        storage.saveTasks(tasks);

        ArrayList<Task> loadedTasks = storage.loadTasks();

        assertEquals(2, loadedTasks.size(), "The number of tasks loaded should match the number saved");
        assertEquals("[T][ ] Task 1", loadedTasks.get(0).toString(), "The first task should match");
        assertEquals("[T][X] Task 2", loadedTasks.get(1).toString(), "The second task should match");
    }

    /**
     * Tests that saving tasks creates the necessary directories and files.
     * 
     * @throws BopesException if an error occurs during saving tasks.
     */
    @Test
    public void testSaveTasksCreatesDirectories() throws BopesException {
        storage.saveTasks(tasks);

        Path path = Path.of(TEST_FILE_PATH);
        assertTrue(Files.exists(path.getParent()), "The directory should be created");
        assertTrue(Files.exists(path), "The file should be created");
    }

    /**
     * Tests loading from a corrupted file to ensure proper error handling.
     * 
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testLoadCorruptedFile() throws Exception {
        Files.writeString(Path.of(TEST_FILE_PATH), "Invalid data format");

        BopesException exception = assertThrows(BopesException.class, () -> {
            storage.loadTasks();
        });

        assertTrue(exception.getMessage()
            .contains("Corrupted data: Insufficient task data in file."), "Exception should indicate loading error");
    }
}
