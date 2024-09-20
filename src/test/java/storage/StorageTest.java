package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import task.ToDo;

public class StorageTest {
    private static final String TEST_DATA_FOLDER = "./data";
    private static final String TEST_FILE_PATH = TEST_DATA_FOLDER + "/test_tasks.txt";

    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        File dataDir = new File(TEST_DATA_FOLDER);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        storage = new Storage(TEST_FILE_PATH);
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    public void testSaveAndLoadTasks() throws IOException {
        Task task = new ToDo("Test task");
        storage.save(new TaskList(List.of(task)));
        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals(task.toString(), loadedTasks.get(0).toString());
    }
}
