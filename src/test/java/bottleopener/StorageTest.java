package bottleopener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final String TEST_FILE_PATH = "data/BottleOpenerTest.txt";
    private Storage storage;
    private Tasklist tasklist;

    @BeforeEach
    void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        tasklist = new Tasklist();
    }

    @Test
    void testLoadNonExistingFile() {
        assertTrue(Files.exists(Path.of(TEST_FILE_PATH)), "File should be created if it does not exist.");
    }

    @Test
    void testLoadExistingFile() throws IOException {
        Files.writeString(Path.of(TEST_FILE_PATH), """
                T|X|Test ToDo Task|
                D| |Test Deadline Task|01/01/2024 0001
                E|X|Test Event Task|31/12/2023 2359|01/01/2024 0000
                """);

        Tasklist loadedTasks = storage.load(tasklist);

        assertEquals(3, loadedTasks.getSize(), "Task list should contain 3 tasks.");
        assertTrue(loadedTasks.getTask(0) instanceof ToDo, "First task should be a ToDo.");
        assertTrue(loadedTasks.getTask(1) instanceof Deadline, "Second task should be a Deadline.");
        assertTrue(loadedTasks.getTask(2) instanceof Event, "Third task should be an Event.");
    }

    @Test
    void testSaveTasks() throws IOException {
        tasklist.addTask(new ToDo("Test ToDo Task"));
        tasklist.addTask(new Deadline("Test Deadline Task", "1/1/2024"));
        tasklist.addTask(new Event("Test Event Task", "31/12/2023", "01/01/2024"));

        storage.save(tasklist);

        String expectedContent = """
                T| |Test ToDo Task|
                D| |Test Deadline Task|01/01/2024 0000
                E| |Test Event Task|31/12/2023 0000|01/01/2024 0000
                """;
        String actualContent = Files.readString(Path.of(TEST_FILE_PATH));
        assertEquals(expectedContent, actualContent, "File content should match the saved task list.");
    }

    @Test
    void testLoadEmptyFile() throws IOException {
        Files.writeString(Path.of(TEST_FILE_PATH), "");

        Tasklist loadedTasks = storage.load(tasklist);

        assertEquals(0, loadedTasks.getSize(), "Task list should be empty when the file is empty.");
    }
}
