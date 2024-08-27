package friday;

import friday.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testLoad() throws IOException {
        ArrayList<Task> tasks = storage.load();
        assertEquals(3, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).isTaskDone());
        assertEquals("Task 2", tasks.get(1).getDescription());
        assertTrue(tasks.get(1).isTaskDone());
        assertEquals("Task 3", tasks.get(2).getDescription());
        assertFalse(tasks.get(2).isTaskDone());
    }
}