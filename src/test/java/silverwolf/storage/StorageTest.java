package silverwolf.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import silverwolf.exception.SilverWolfException;
import silverwolf.task.Task;
import silverwolf.task.Todo;

/**
 * Tests for the Storage class to verify reading and writing of data to and from file.
 */
class StorageTest {

    private static final String TEST_DIR = "testdata";
    private static final String TEST_FILE = TEST_DIR + "/testfile.txt";
    private Storage storage;

    /**
     * Initializes the Storage instance before each test.
     * Ensures the directory and file are cleaned and set up for each test.
     */
    @BeforeEach
    void setUp() {
        storage = new Storage(TEST_FILE);
        // Ensure directory and file are cleaned before each test
        deleteTestFile();
        storage.setUp();
    }

    /**
     * Cleans up the test file and directory after each test.
     */
    @AfterEach
    void tearDown() {
        // Clean up the test file after each test
        deleteTestFile();
    }

    /**
     * Deletes the test file and directory if the directory is empty.
     */
    private void deleteTestFile() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        File directory = new File(TEST_DIR);
        if (directory.exists() && directory.list().length == 0) {
            directory.delete();
        }
    }

    /**
     * Tests that tasks can be saved to a file and then loaded back correctly.
     * Verifies that the saved tasks are the same as the loaded tasks.
     *
     * @throws SilverWolfException If an error occurs during saving or loading.
     */
    @Test
    void testSaveAndLoadTasks() throws SilverWolfException {
        // Prepare test data
        List<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Complete UTC2728 CA1"));
        tasksToSave.add(new Todo("Eat dinner at DH"));
        // Save tasks to file
        storage.save(tasksToSave);
        // Load tasks from file
        List<Task> loadedTasks = storage.load();
        // Verify that the saved tasks are the same as the loaded tasks
        assertEquals(tasksToSave.size(), loadedTasks.size(), "The number of tasks should be the same");
        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(tasksToSave.get(i).toFileString(),
                    loadedTasks.get(i).toFileString(), "Tasks should be the same");
        }
    }

    /**
     * Tests loading tasks from an empty file.
     * Verifies that no tasks are loaded from an empty file.
     *
     * @throws SilverWolfException If an error occurs during loading.
     */
    @Test
    void testLoadEmptyFile() throws SilverWolfException {
        // Create an empty file and load tasks
        List<Task> loadedTasks = storage.load();

        // Verify that no tasks are loaded
        assertTrue(loadedTasks.isEmpty(), "Loaded tasks should be empty");
    }

    /**
     * Tests that saving tasks to an invalid file path throws a SilverWolfException.
     * Verifies the exception handling for invalid file paths.
     */
    @Test
    void testSaveThrowsExceptionOnInvalidPath() {
        // Create a Storage object with an invalid path
        Storage invalidStorage = new Storage("invalid/path/to/file.txt");

        // Verify that saving throws an exception
        assertThrows(SilverWolfException.class, () -> invalidStorage.save(new ArrayList<>()),
                "Saving tasks to an invalid path should throw SilverWolfException");
    }
}
