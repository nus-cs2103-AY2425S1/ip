package jaytest.storage;

import jay.storage.DataIOException;
import jay.storage.InvalidDataFormatException;
import jay.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import jay.task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void testSaveTasks() {
        String taskList = "T | 0 | read book\n";

        Storage storage = new Storage("data", "tasks.txt");

        try {
            storage.saveTasks(taskList);
        } catch (DataIOException e) {
            fail("DataIOException should not be thrown.");
        }
    }

    @Test
    @AfterEach
    public void testLoadTasksFromExistsFile() {
        Storage storage = new Storage("data", "test.txt");

        try {
            ArrayList<Task> tasks = storage.loadTasks();
            assertEquals(1, tasks.size());
        } catch (DataIOException e) {
            fail("DataIOException should not be thrown.");
        } catch (InvalidDataFormatException e) {
            fail("InvalidDataFormatException should not be thrown.");
        }
    }

    @Test
    public void testLoadTasksFromNonExistsFile() {
        Storage storage = new Storage("data", "non-exists.txt");

        try {
            storage.loadTasks();
            fail("DataIOException should be thrown.");
        } catch (DataIOException e) {
            assertEquals("OOPS!!! The file does not exist.", e.getMessage());
        } catch (InvalidDataFormatException e) {
            fail("InvalidDataFormatException should not be thrown.");
        }
    }
}
