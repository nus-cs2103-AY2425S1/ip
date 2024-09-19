package friday.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;
import friday.task.Todo;

/**
 * Tests the functionality of the Storage class.
 * Verifies that tasks can be correctly loaded from a file and that the Storage class operates as expected.
 */
public class StorageTest {

    private Storage storage;
    private final String testFilePath = "test_storage.txt";

    /**
     * Sets up the test environment by creating a Storage object
     * and writing sample tasks to the test file before each test is run.
     *
     * @throws IOException If an error occurs while writing to the file.
     */
    @BeforeEach
    public void setUp() throws IOException {
        storage = new Storage(testFilePath);
        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write("T | 0 | Task 1\n");
            writer.write("D | 1 | Task 2 | 2023-10-10 1800\n");
            writer.write("E | 0 | Task 3 | 2023-10-10 1800 | 2023-10-11 1900\n");
        }
    }

    /**
     * Tests the loading of tasks from the file.
     * Verifies that the tasks are correctly loaded and their properties match the expected values.
     *
     * @throws IOException If an error occurs while loading from the file.
     */
    @Test
    public void load_success() throws IOException {
        ArrayList<Task> tasks = storage.load();
        assertEquals(3, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).isTaskDone());
        assertEquals("Task 2", tasks.get(1).getDescription());
        assertTrue(tasks.get(1).isTaskDone());
        assertEquals("Task 3", tasks.get(2).getDescription());
        assertFalse(tasks.get(2).isTaskDone());
    }

    /**
     * Tests the saving of tasks to the file.
     * Verifies that the tasks are correctly saved and their properties match the expected values.
     *
     * @throws IOException If an error occurs while saving to the file.
     */
    @Test
    public void save_success() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Deadline("Task 2", "2023-10-10 1800", true));
        tasks.add(new Event("Task 3", "2023-10-10 1800", "2023-10-11 1900"));

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(3, loadedTasks.size());
        assertEquals("Task 1", loadedTasks.get(0).getDescription());
        assertFalse(loadedTasks.get(0).isTaskDone());
        assertEquals("Task 2", loadedTasks.get(1).getDescription());
        assertTrue(loadedTasks.get(1).isTaskDone());
        assertEquals("Task 3", loadedTasks.get(2).getDescription());
        assertFalse(loadedTasks.get(2).isTaskDone());
    }
}
