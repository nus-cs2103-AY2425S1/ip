package assistinator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import assistinator.tasks.DeadlineTask;
import assistinator.tasks.EventTask;
import assistinator.tasks.Task;
import assistinator.tasks.TodoTask;

/**
 * Test class for the Storage class.
 * This class contains unit tests to ensure that the Storage methods
 * function correctly for various scenarios.
 */
class StorageTest {

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @TempDir
    Path tempDir;

    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Sets up a new Storage instance and a list of tasks before each test.
     */
    @BeforeEach
    void setUp() {
        String filePath = tempDir.resolve("tasks.txt").toString();
        storage = new Storage(filePath);
        tasks = new ArrayList<>();
    }

    /**
     * Tests saving and loading an empty task list.
     */
    @Test
    void testSaveAndLoadEmptyTaskList() throws AssistinatorException {
        storage.saveTasks(tasks);
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertTrue(loadedTasks.isEmpty());
    }

    /**
     * Tests saving and loading a task list with a TodoTask.
     */
    @Test
    void testSaveAndLoadTodoTask() throws AssistinatorException {
        Task todoTask = new TodoTask("Buy groceries");
        tasks.add(todoTask);
        storage.saveTasks(tasks);
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof TodoTask);
        assertEquals("Buy groceries", loadedTasks.get(0).getDescription());
    }

    /**
     * Tests saving and loading a task list with a DeadlineTask.
     */
    @Test
    void testSaveAndLoadDeadlineTask() throws AssistinatorException {
        LocalDateTime deadline = LocalDateTime.now().plusDays(1);
        Task deadlineTask = new DeadlineTask("Submit report", deadline);
        tasks.add(deadlineTask);
        storage.saveTasks(tasks);
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof DeadlineTask);
        assertEquals("Submit report", loadedTasks.get(0).getDescription());
        assertEquals(
                deadline.format(formatter), ((DeadlineTask) loadedTasks.get(0)).getDeadline().format(formatter)
        );
    }

    /**
     * Tests saving and loading a task list with an EventTask.
     */
    @Test
    void testSaveAndLoadEventTask() throws AssistinatorException {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Task eventTask = new EventTask("Team meeting", start, end);
        tasks.add(eventTask);
        storage.saveTasks(tasks);
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof EventTask);
        assertEquals("Team meeting", loadedTasks.get(0).getDescription());
        assertEquals(start.format(formatter), ((EventTask) loadedTasks.get(0)).getStartTime().format(formatter));
        assertEquals(end.format(formatter), ((EventTask) loadedTasks.get(0)).getEndTime().format(formatter));
    }

    /**
     * Tests saving and loading a task list with multiple tasks of different types.
     */
    @Test
    void testSaveAndLoadMultipleTasks() throws AssistinatorException {
        tasks.add(new TodoTask("Buy groceries"));
        tasks.add(new DeadlineTask("Submit report", LocalDateTime.now().plusDays(1)));
        tasks.add(new EventTask(
                "Team meeting",
                LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2)
                )
        );
        storage.saveTasks(tasks);
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(3, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof TodoTask);
        assertTrue(loadedTasks.get(1) instanceof DeadlineTask);
        assertTrue(loadedTasks.get(2) instanceof EventTask);
    }

    /**
     * Tests loading tasks from a non-existent file.
     */
    @Test
    void testLoadTasksFromNonExistentFile() throws AssistinatorException {
        String nonExistentFilePath = tempDir.resolve("non_existent.txt").toString();
        Storage nonExistentStorage = new Storage(nonExistentFilePath);
        ArrayList<Task> loadedTasks = nonExistentStorage.loadTasks();
        assertTrue(loadedTasks.isEmpty());
    }
}
