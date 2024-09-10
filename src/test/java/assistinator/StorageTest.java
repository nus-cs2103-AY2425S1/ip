package assistinator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String TEST_FILE_PATH = "test_tasks.txt";
    @Test
    public void loadTasks_fileNotFound_exceptionThrown() {
        Storage storage = new Storage("non_existent_file.txt");

        try {
            storage.loadTasks();
            fail("Expected AssitinatorExceptions to be thrown");
        } catch (AssitinatorExceptions e) {
            assertEquals("File not found", e.getMessage());
        }
    }

    @Test
    public void getTask_invalidTaskType_exceptionThrown() {
        Storage storage = new Storage(TEST_FILE_PATH);
        String[] parts = {"X", "NOTDONE", "Invalid task"};

        try {
            storage.getTask(parts, "X");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown task type: X", e.getMessage());
        }
    }
}
