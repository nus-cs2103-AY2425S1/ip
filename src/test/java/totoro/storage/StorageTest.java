/**package sage.storage;

import org.junit.jupiter.api.*;
import sage.exception.SageException;
import sage.task.Task;
import sage.task.TaskList;
import sage.task.ToDo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private static final String TEST_FILE_PATH = "test_tasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Files.deleteIfExists(Path.of(TEST_FILE_PATH));
    }

    @Test
    public void testSaveTasks() throws SageException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("buy milk"));
        tasks.addTask(new ToDo("borrow book"));

        assertDoesNotThrow(() -> storage.saveTasks(tasks));

        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "The file should exist after saving tasks");
        assertTrue(file.length() > 0, "The file should not be empty after saving tasks");
    }

    @Test
    public void testLoadTasks_fileExists() throws SageException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("buy milk"));
        tasks.addTask(new ToDo("borrow book"));
        storage.saveTasks(tasks);

        List<Task> loadedTasks = storage.load();
        assertEquals(2, loadedTasks.size(),
                "The loaded tasks list should contain two tasks");
        assertEquals(tasks.get(0).toString(), loadedTasks.get(0).toString(),
                "The first loaded tasks should match the first saved tasks");
        assertEquals(tasks.get(1).toString(), loadedTasks.get(1).toString(),
                "The second loaded tasks should match the second saved tasks");
    }

    /**@Test
    public void testLoadTasks_fileDoesNotExist() {
        assertDoesNotThrow(() -> {
            List<Task> loadedTasks = storage.load();
            assertEquals(0, loadedTasks.size(),
                    "The loaded tasks list should be empty if the file does not exist");
        });
    }
     */

    /**@Test
    public void testLoadTasks_fileCorrupted() throws Exception {
        Files.writeString(Path.of(TEST_FILE_PATH), "invalid data\nmore invalid data");

        SageException exception = assertThrows(SageException.class, () -> storage.load(),
                "Expected SageException when loading from a corrupted file");
        assertTrue(exception.getMessage().contains("Error loading tasks from file"),
                "The error message should indicate a problem when loading the file");
    }
    */
/**} */
