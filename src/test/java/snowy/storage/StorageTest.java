package snowy.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import snowy.data.SnowyException;
import snowy.tasklist.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @TempDir
    public static Path testFolder;

    private static final String NON_EXISTENT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void constructor_invalidPath_createsDirectoryAndFile() {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        assertTrue(Files.exists(testFolder.resolve("tasks.txt")));
    }

    @Test
    public void writeTaskToFile_validTask_taskIsSaved() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        Todo todo = new Todo("Test Task");

        storage.writeTaskToFile(todo);

        List<String> tasks = storage.loadTasksFromFile();
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Test Task", tasks.get(0));
    }

    @Test
    public void deleteTaskFromFile_taskExists_taskIsDeleted() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        Todo todo = new Todo("Test Task");
        storage.writeTaskToFile(todo);

        storage.deleteTaskFromFile(todo);

        List<String> tasks = storage.loadTasksFromFile();
        assertEquals(0, tasks.size());
    }

    @Test
    public void loadTasksFromFile_fileExists_loadsTasksCorrectly() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        Todo todo1 = new Todo("Task1");
        Todo todo2 = new Todo("Task2");

        storage.writeTaskToFile(todo1);
        storage.writeTaskToFile(todo2);

        List<String> tasks = storage.loadTasksFromFile();
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] Task1", tasks.get(0));
        assertEquals("[T][ ] Task2", tasks.get(1));
    }

    @Test
    public void loadTasksFromFile_nonExistentFile_returnsEmptyList() {
        Storage storage = new Storage(testFolder.toString(), NON_EXISTENT_FILE_NAME);

        List<String> tasks = storage.loadTasksFromFile();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void save_nullTask_exceptionThrown() {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        assertThrows(NullPointerException.class, () -> storage.writeTaskToFile(null));
    }
}
