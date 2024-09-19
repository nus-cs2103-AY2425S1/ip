package snowy.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import snowy.data.SnowyException;
import snowy.tasklist.Task;
import snowy.tasklist.TaskList;
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

    private static final String TEST_DIRECTORY = "test_storage";
    private static final String NON_EXISTENT_FILE_NAME = "ThisFileDoesNotExist.txt";



    @Test
    public void constructor_invalidPath_createsDirectoryAndFile() {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        assertTrue(Files.exists(testFolder.resolve("tasks.txt")));
    }

    @Test
    public void writeTaskToFile_validTask_taskIsSaved() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        TaskList taskList = new TaskList(storage);
        Todo todo = new Todo("Test Task");

        taskList.addTask(todo);

        TaskList newTaskList = new TaskList(storage);
        assertEquals(1, newTaskList.getSize());
        assertEquals("[T][ ] Test Task", newTaskList.getTask(0).toString());
    }

    @Test
    public void deleteTaskFromFile_taskExists_taskIsDeleted() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        TaskList taskList = new TaskList(storage);
        Todo todo = new Todo("Test Task");

        taskList.addTask(todo);  // Add task to file
        assertEquals(1, taskList.getSize());

        // Delete the task
        taskList.deleteTask(1);
        assertEquals(0, taskList.getSize());

        // Reload the tasks from the file
        TaskList newTaskList = new TaskList(storage);
        assertEquals(0, newTaskList.getSize());
    }

    @Test
    public void loadTasksFromFile_fileExists_loadsTasksCorrectly() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        TaskList taskList = new TaskList(storage);
        Todo todo1 = new Todo("Task1");
        Todo todo2 = new Todo("Task2");

        taskList.addTask(todo1);
        taskList.addTask(todo2);

        // Reload tasks from file
        TaskList newTaskList = new TaskList(storage);

        assertEquals(2, newTaskList.getSize());
        assertEquals("[T][ ] Task1", newTaskList.getTask(0).toString());
        assertEquals("[T][ ] Task2", newTaskList.getTask(1).toString());
    }

    @Test
    public void loadTasksFromFile_nonExistentFile_returnsEmptyList() throws SnowyException {
        Storage storage = new Storage(testFolder.toString(), NON_EXISTENT_FILE_NAME);
        TaskList taskList = new TaskList(storage);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void save_nullTask_exceptionThrown() {
        Storage storage = new Storage(testFolder.toString(), "tasks.txt");
        assertThrows(NullPointerException.class, () -> storage.writeTaskToFile(null));
    }
}
