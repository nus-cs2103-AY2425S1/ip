package totoro.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import totoro.exception.TotoroException;
import totoro.exception.TotoroFileFormatException;
import totoro.task.Task;
import totoro.task.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private final String filePath = "testStorage.txt";
    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setUp() {
        storage = new Storage(filePath);
        testFile = new File(filePath);
    }

    @AfterEach
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testSaveAndLoadTasks() throws TotoroException {
        // Create test tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("buy groceries"));
        tasks.add(new ToDo("read book"));

        // Save tasks to file
        storage.saveTasks(tasks);

        // Load tasks from file
        ArrayList<Task> loadedTasks = storage.load();

        // Check that tasks are correctly saved and loaded
        assertEquals(2, loadedTasks.size());
        assertEquals("buy groceries", tasks.get(0).getDescription());
        assertEquals("read book", tasks.get(1).getDescription());
    }

    @Test
    public void testLoadTasks_invalidFormat() throws Exception {
        // Write invalid data to the test file
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testFile))) {
            bufferedWriter.write("Invalid task format");
        } catch (Exception e) {
            fail("Failed to set up invalid test data");
        }

        // Expect TotoroFileFormatException to be thrown
        assertThrows(TotoroFileFormatException.class, () -> {
            storage.load();
        });
    }
}
