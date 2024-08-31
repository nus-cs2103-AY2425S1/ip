package yapper.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yapper.task.Deadline;
import yapper.task.Event;
import yapper.task.Task;
import yapper.task.Todo;
import yapper.exception.YapperException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    private static final String TEST_FILE_PATH = "src/test/java/yapper/storage/test_data.txt";
    private Storage storage;
    private ArrayList<Task> tasks;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        tasks = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void save_loadTasks_success() throws IOException, YapperException {
        tasks.add(new Todo("Buy milk"));
        tasks.add(new Deadline("Submit report", "2024-10-10 1800"));
        tasks.add(new Event("Team meeting", "2024-11-10 1400", "2024-11-10 1600"));

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(tasks.size(), loadedTasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(tasks.get(i).toSaveFormat(), loadedTasks.get(i).toSaveFormat());
        }
    }
}
