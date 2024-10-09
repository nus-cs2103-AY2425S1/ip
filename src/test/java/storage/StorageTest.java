package storage;

import exceptions.JarException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.DeadLine;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final String TEST_DIRECTORY = "testdata";
    private static final String TEST_FILE_PATH = "testdata/tasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        File testDirectory = new File(TEST_DIRECTORY);
        if (!testDirectory.exists()) {
            testDirectory.mkdirs();
        }

        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    public void load_noExistingFile_returnsEmptyTaskList() throws IOException, JarException {
        List<Task> tasks = storage.load();
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void save_thenLoad_tasksPersistCorrectly() throws IOException, JarException {
        List<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new ToDo("Read book"));
        LocalDateTime deadlineDateTime = LocalDateTime.of(2023, 8, 24, 23, 59);
        tasksToSave.add(new DeadLine("Submit assignment", deadlineDateTime));

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.load();
        assertEquals(2, loadedTasks.size());

        assertEquals("[T][ ] Read book", loadedTasks.get(0).toString());
        assertEquals("[D][ ] Submit assignment (by: Aug 24 2023, 23:59)", loadedTasks.get(1).toString());
    }

    @Test
    public void load_corruptedFile_throwsJarException() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_FILE_PATH)) {
            writer.write("T | 1 | Read book\n"); // valid line
            writer.write("X | 1 | Submit assignment | 2/12/2023 2359\n"); // Invalid task type, X
            writer.write("sjfsjfhjsnl"); // Corrupted line
        }

        JarException exception = assertThrows(JarException.class, () -> {
            storage.load();
        });

        assertEquals("Data file corrupted. Invalid task type.", exception.getMessage());
    }

}
