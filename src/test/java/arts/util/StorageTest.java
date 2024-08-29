package arts.util;

import arts.ArtsException;
import arts.task.Task;
import arts.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Storage storage;
    private final String filePath = "test_tasks.txt";

    @BeforeEach
    public void setUp() {
        storage = new Storage(filePath);
        new File(filePath).delete();
    }

    @Test
    public void testLoad_noFile() throws ArtsException {
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Loading from a non-existent file should return an empty task list.");
    }

    @Test
    public void testSaveAndLoad() throws ArtsException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Todo("Test Task"); // Use the Todo subclass
        tasks.add(task);

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size(), "There should be one task loaded.");
        assertEquals(task.toFileFormat(), loadedTasks.get(0).toFileFormat(), "The loaded task should match the saved task.");
    }
}