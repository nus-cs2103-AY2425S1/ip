package arts.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arts.ArtsException;
import arts.task.Task;
import arts.task.Todo;

/**
 * Test class for the Storage class.
 * This class contains unit tests for the methods in the Storage class,
 * verifying file storage and retrieval functionality.
 */
public class StorageTest {

    private Storage storage;
    private final String filePath = "test_tasks.txt";

    /**
     * Sets up the test environment before each test.
     * Initializes the Storage instance and ensures the test file is deleted.
     */
    @BeforeEach
    public void setUp() {
        storage = new Storage(filePath);
        new File(filePath).delete();
    }

    /**
     * Tests the load() method when no file exists.
     * Verifies that loading from a non-existent file returns an empty task list.
     *
     * @throws ArtsException if there is an error loading the tasks.
     */
    @Test
    public void testLoad_noFile() throws ArtsException {
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Loading from a non-existent file should return an empty task list.");
    }

    /**
     * Tests the save() and load() methods.
     * Verifies that a task can be saved to a file and then correctly loaded back.
     *
     * @throws ArtsException if there is an error saving or loading the tasks.
     */
    @Test
    public void testSaveAndLoad() throws ArtsException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Todo("Test Task"); // Use the Todo subclass
        tasks.add(task);

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size(), "There should be one task loaded.");
        assertEquals(task.toFileFormat(), loadedTasks.get(0).toFileFormat(),
                "The loaded task should match the saved task.");
    }
}
