package bigdog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private Storage storage;
    private final String testFilePath = "testStorage.txt";

    /**
     * Initializes the Storage instance before each test.
     */
    @BeforeEach
    void setUp() {
        storage = new Storage(testFilePath);
    }

    /**
     * Deletes the test file after each test to ensure a clean state.
     */
    @BeforeEach
    void tearDown() {
        // Delete the test file after each test
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Tests saving and loading of tasks to ensure that tasks are saved and
     * loaded correctly.
     *
     * @throws BigdogException if there is an issue with saving or loading tasks
     */
    @Test
    void saveAndLoadTasks_success() throws BigdogException {
        // Create some sample tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(Todo.of("Task 1"));
        tasks.add(Deadline.of("Task 2 /by 10/09/2024"));
        tasks.add(Event.of("Task 3 /from 12/09/2024 18:00 /to 12/09/2024 20:00"));

        // Save tasks
        storage.save(tasks);

        // Load tasks
        ArrayList<Task> loadTasks = storage.load();

        // Verify the loaded tasks are equal to the original tasks
        assertEquals(3, loadTasks.size());
        assertEquals(tasks.get(0).getDescription(), loadTasks.get(0).getDescription());
        assertEquals(tasks.get(1).getDescription(), loadTasks.get(1).getDescription());
        assertEquals(tasks.get(2).getDescription(), loadTasks.get(2).getDescription());
    }

    /**
     * Tests loading tasks from a non-existent file to ensure that a
     * BigdogException is thrown.
     */
    @Test
    void load_nonExistentFile_throwsException() {
        // Create a storage instance with a non-existent file path
        Storage storage = new Storage("nonExistentFile.txt");

        // Expect BigdogException due to FileNotFoundException
        assertThrows(BigdogException.class, storage::load);
    }

    /**
     * Tests saving and loading tasks with marked tasks to ensure that
     * the marked state of tasks is correctly preserved.
     *
     * @throws BigdogException if there is an issue with saving or loading tasks
     */
    @Test
    void saveAndLoadTasks_withMarkedTasks() throws BigdogException {
        // Create some sample tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(Todo.of("Task 1"));
        tasks.add(Deadline.of("Task 2 /by 10/09/2024"));
        tasks.get(0).mark(); // Mark the first task as done

        // Save tasks
        storage.save(tasks);

        // Load tasks
        ArrayList<Task> loadedTasks = storage.load();

        // Verify the loaded tasks are equal to the original tasks
        assertEquals(2, loadedTasks.size());
        assertTrue(loadedTasks.get(0).isMarked());
        assertFalse(loadedTasks.get(1).isMarked());
    }

    /**
     * Tests saving and loading tasks with corrupted data to ensure that
     * a BigdogException is thrown when data is corrupted.
     *
     * @throws IOException if there is an issue with writing the corrupted data
     */
    @Test
    void saveAndLoadTasks_corruptedData_throwsException() throws IOException {
        // Simulate a corrupted file
        Files.writeString(new File(testFilePath).toPath(), "Invalid Data");

        // Expect BigdogException due to corrupted data
        assertThrows(BigdogException.class, storage::load);
    }
}

