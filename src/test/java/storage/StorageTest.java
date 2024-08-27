package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private Storage storage;
    private static final String TEST_FILE_PATH = "./data/test_tasks.txt";

    @BeforeEach
    public void setUp() throws IOException {
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