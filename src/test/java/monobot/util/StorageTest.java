package monobot.util;

import monobot.exception.MonoBotException;
import monobot.task.Deadline;
import monobot.task.Event;
import monobot.task.Task;
import monobot.task.Todo;

import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    @TempDir
    Path tempDir;

    private Storage storage;

    @BeforeEach
    void setUp() {
        String testFilePath = tempDir.resolve("test_tasks.txt").toString();
        storage = new Storage(testFilePath);
    }

    @Test
    void testSaveAndLoadTasks() throws MonoBotException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Buy groceries"));
        tasksToSave.add(new Deadline("Submit report", "1/5/2023 1400"));
        tasksToSave.add(new Event("Team meeting", "2/5/2023 1000", "2/5/2023 1100"));

        storage.save(tasksToSave);

        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(tasksToSave.size(), loadedTasks.size());
        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(tasksToSave.get(i).getDescription(), loadedTasks.get(i).getDescription());
            assertEquals(tasksToSave.get(i).getIsDone(), loadedTasks.get(i).getIsDone());
        }
    }

    @Test
    void testSaveAndLoadWithMarkedTasks() throws MonoBotException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        Todo todo = new Todo("Clean room");
        todo.markTask();
        tasksToSave.add(todo);

        Deadline deadline = new Deadline("Pay bills", "3/5/2023 1800");
        deadline.markTask();
        tasksToSave.add(deadline);

        storage.save(tasksToSave);

        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(tasksToSave.size(), loadedTasks.size());
        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(tasksToSave.get(i).getDescription(), loadedTasks.get(i).getDescription());
            assertTrue(loadedTasks.get(i).getIsDone());
        }
    }

    @Test
    void testLoadNonExistentFile() throws MonoBotException {
        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
    }

    @Test
    void testSaveAndLoadEmptyList() throws MonoBotException {
        ArrayList<Task> emptyList = new ArrayList<>();
        storage.save(emptyList);

        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
    }

    @Test
    void testSaveIoException() {
        Storage invalidStorage = new Storage("/invalid/path/tasks.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test task"));

        assertThrows(MonoBotException.class, () -> invalidStorage.save(tasks));
    }
}

