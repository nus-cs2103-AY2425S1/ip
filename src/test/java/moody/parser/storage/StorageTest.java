package moody.parser.storage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class StorageTest {

    private Storage storage;
    private final String filePath = "./data/test.txt";

    @BeforeEach
    void setUp() {
        storage = new Storage(filePath);
    }

    @Test
    void testSaveAndLoad() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read book"));

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Todo);
    }

    @Test
    void testSaveAndLoadEmptyList() throws IOException {
        ArrayList<Task> emptyTasks = new ArrayList<>();
        storage.save(emptyTasks);

        // Load tasks from the storage
        ArrayList<Task> loadedTasks = storage.load();

        // Verify that the loaded list is also empty
        assertEquals(0, loadedTasks.size());
    }
}
