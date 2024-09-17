package axel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private Storage storage;
    private File testFile;
    @BeforeEach
    public void setUp() {
        testFile = new File("test_tasks.txt");
        storage = new Storage(testFile.getAbsolutePath());
    }
    @AfterEach
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }
    @Test
    public void testSaveAndLoadTasks() throws CorruptedFileException, IOException {
        List<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new ToDoTask("read book"));
        tasksToSave.add(new DeadlineTask("return book", "2023-09-01 1800"));
        tasksToSave.add(new EventTask("project meeting", "2023-09-01 1400", "2023-09-01 1600"));
        storage.save(tasksToSave);
        List<Task> loadedTasks = storage.load();
        assertEquals(tasksToSave.size(), loadedTasks.size());
        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(tasksToSave.get(i).toString(), loadedTasks.get(i).toString());
        }
    }
    @Test
    public void testLoadFromEmptyFile() throws CorruptedFileException, IOException {
        testFile.createNewFile();
        List<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
    }
    @Test
    public void testLoadFromCorruptedFile() throws IOException {
        Files.write(testFile.toPath(), "corrupted data".getBytes());
        assertThrows(CorruptedFileException.class, () -> {
            storage.load();
        });
    }
}