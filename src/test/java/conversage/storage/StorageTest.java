package conversage.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import conversage.exception.ConverSageException;
import conversage.task.Task;
import conversage.task.ToDo;

class StorageTest {

    private Storage storage;
    private File testFile;

    @BeforeEach
    void setUp() {
        testFile = new File("data/testTasks.txt");
        storage = new Storage(testFile.getPath());
    }

    @AfterEach
    void tearDown() {
        testFile.delete();
    }

    @Test
    void load_emptyFile_success() throws ConverSageException {
        List<Task> tasks = storage.load();
        assertEquals(0, tasks.size());
    }

    @Test
    void save_and_load_success() throws ConverSageException, IOException {
        ToDo todo = new ToDo("test task");
        List<Task> tasks = List.of(todo);
        storage.save(tasks);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] test task", loadedTasks.get(0).toString());
    }

    @Test
    void load_corruptedFile_success() throws ConverSageException, IOException {
        // Create a corrupted file
        try (java.io.PrintWriter writer = new java.io.PrintWriter(testFile)) {
            writer.println("InvalidTaskType | Not Done | test task");
        }

        List<Task> tasks = storage.load();
        assertEquals(0, tasks.size()); // Corrupted lines should be skipped
    }

    @Test
    void save_emptyList_success() throws ConverSageException {
        List<Task> tasks = List.of();
        storage.save(tasks);

        List<Task> loadedTasks = storage.load();
        assertEquals(0, loadedTasks.size());
    }

    @Test
    void save_largeList_success() throws ConverSageException {
        List<Task> tasks = List.of(
                new ToDo("task 1"),
                new ToDo("task 2"),
                new ToDo("task 3"),
                new ToDo("task 4"),
                new ToDo("task 5")
        );
        storage.save(tasks);

        List<Task> loadedTasks = storage.load();
        assertEquals(5, loadedTasks.size());
        for (int i = 0; i < 5; i++) {
            assertEquals("[T][ ] task " + (i + 1), loadedTasks.get(i).toString());
        }
    }

    @Test
    void load_nonExistentFile_success() throws ConverSageException {
        File nonExistentFile = new File("data/nonExistentTasks.txt");
        Storage nonExistentStorage = new Storage(nonExistentFile.getPath());

        List<Task> tasks = nonExistentStorage.load();
        assertEquals(0, tasks.size());
    }
}
