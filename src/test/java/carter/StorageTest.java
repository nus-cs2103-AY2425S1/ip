package carter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {
    @TempDir
    private Path tempDir;

    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setup() {
        testFile = tempDir.resolve("testTasks.txt").toFile();
        storage = new Storage(testFile.getPath());
    }

    @Test
    public void testSaveNullTaskList() {
        assertThrows(AssertionError.class, () -> storage.save(null), "Should throw assertion error for null task list");
    }

    @Test
    void testLoadEmptyFile() throws CarterException {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Tasks list should be empty for a new file");
    }
}
