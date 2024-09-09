package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeff.task.Task;

public class StorageTest {
    private static final String TEST_DIR_PATH = "/data";
    private static final String TEST_FILE_PATH = "/test_tasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_DIR_PATH, TEST_FILE_PATH);
    }

    @Test
    public void loadData_nonExistentFile_returnsEmptyList() throws IOException {
        Storage storage = new Storage("/data", "/nonexistent_file.txt");
        ArrayList<Task> tasks = storage.loadData();
        assertEquals(0, tasks.size());
    }
}
