package bopes;

import bopes.exception.BopesException;
import bopes.task.Task;
import bopes.task.TaskList;
import bopes.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final String TEST_FILE_PATH = "test_data/tasks.txt";
    private Storage storage;
    private TaskList tasks;

    @BeforeEach
    public void setUp() throws Exception {
        // Create a Storage object pointing to a test file
        storage = new Storage(TEST_FILE_PATH);
        tasks = new TaskList(new ArrayList<>());

        // Ensure the test directory and file are clean before each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Clean up the test directory and file after each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        File directory = new File(file.getParent());
        if (directory.exists()) {
            directory.delete();
        }
    }

    @Test
    public void testLoadTasksNoFile() throws BopesException {
        // Test loading tasks when the file does not exist
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertTrue(loadedTasks.isEmpty(), "Tasks should be empty if no file exists");
    }

    @Test
    public void testSaveAndLoadTasks() throws BopesException {
        // Add some tasks to the TaskList
        tasks.addTask(new ToDo("Task 1", false));
        tasks.addTask(new ToDo("Task 2", true));
        
        // Save the tasks to the file
        storage.saveTasks(tasks);

        // Load the tasks back from the file
        ArrayList<Task> loadedTasks = storage.loadTasks();

        // Verify that the tasks were saved and loaded correctly
        assertEquals(2, loadedTasks.size(), "The number of tasks loaded should match the number saved");

        assertEquals("[T][ ] Task 1", loadedTasks.get(0).toString(), "The first task should match");
        assertEquals("[T][X] Task 2", loadedTasks.get(1).toString(), "The second task should match");
    }

    @Test
    public void testSaveTasksCreatesDirectories() throws BopesException {
        // Test that saving tasks creates the necessary directories
        storage.saveTasks(tasks);

        // Verify that the directory and file have been created
        Path path = Path.of(TEST_FILE_PATH);
        assertTrue(Files.exists(path.getParent()), "The directory should be created");
        assertTrue(Files.exists(path), "The file should be created");
    }

    @Test
    public void testLoadCorruptedFile() throws Exception {
        // Create a corrupted file
        Files.writeString(Path.of(TEST_FILE_PATH), "Invalid data format");

        // Attempt to load tasks from the corrupted file
        BopesException exception = assertThrows(BopesException.class, () -> {
            storage.loadTasks();
        });

        assertTrue(exception.getMessage().contains("Corrupted data: Insufficient task data in file."), "Exception should indicate loading error");
    }
}
