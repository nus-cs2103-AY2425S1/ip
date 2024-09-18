package stobberi.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stobberi.task.Deadline;
import stobberi.task.Event;
import stobberi.task.Task;
import stobberi.task.Todo;
import stobberi.stobberiexception.NotPossibleDurationStobberiException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StorageTest {

    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        testFile = File.createTempFile("testTasks", ".txt");
        testFile.deleteOnExit();
        storage = new Storage(testFile.getPath());
    }

    @AfterEach
    public void tearDown() {
        // Clean up after tests
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testGetList_emptyFile() {
        ArrayList<Task> taskList = storage.getList();
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testSaveList_singleTask() throws IOException, NotPossibleDurationStobberiException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new Todo("Read book", "2024-09-18");
        tasks.add(todo);
        storage.saveList(tasks);

        ArrayList<Task> readTasks = storage.getList();
        assertEquals(1, readTasks.size());
        assertEquals(todo.getDescription(), readTasks.get(0).getDescription());
        assertFalse(readTasks.get(0).isDone());
    }

    @Test
    public void testSaveList_multipleTasks() throws IOException, NotPossibleDurationStobberiException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new Todo("Read book", "1");
        Task deadline = new Deadline("Submit report", "19-09-2024 0900hrs", "1");
        Task event = new Event("Conference", "21-09-2024 0900hrs", "21-09-2024 1700hrs", "1");

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);
        storage.saveList(tasks);

        ArrayList<Task> readTasks = storage.getList();
        assertEquals(3, readTasks.size());

        assertTrue(readTasks.get(0) instanceof Todo);
        assertTrue(readTasks.get(1) instanceof Deadline);
        assertTrue(readTasks.get(2) instanceof Event);

        assertEquals("Read book", readTasks.get(0).getDescription());
        assertEquals("Submit report", readTasks.get(1).getDescription());
        assertEquals("Conference", readTasks.get(2).getDescription());
    }

    @Test
    public void testGetList_invalidFormat() throws IOException {
        // Write invalid format to the file
        try (var writer = new java.io.BufferedWriter(new java.io.FileWriter(testFile))) {
            writer.write("Invalid data\n");
        }

        ArrayList<Task> taskList = storage.getList();
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testSaveAndLoadTasks_withDifferentAttributes() throws IOException, NotPossibleDurationStobberiException {
        ArrayList<Task> tasks = new ArrayList<>();

        // Create tasks with various attributes
        Task todo = new Todo("Complete homework", "2024-09-18"); // No completion status parameter for Todo
        Task deadline = new Deadline("Submit assignment", "19-09-2024 0900hrs", "1"); // Completed
        Task event = new Event("Meeting", "21-09-2024 1000hrs", "21-09-2024 1200hrs", "1"); // Completed

        // Mark Todo as done
        todo.setDone();

        // Add tasks to the list
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        // Save tasks to the file
        storage.saveList(tasks);

        // Load tasks from the file
        ArrayList<Task> loadedTasks = storage.getList();

        // Verify the tasks were loaded correctly
        assertEquals(3, loadedTasks.size());

        // Check Todo task
        Task loadedTodo = loadedTasks.get(0);
        assertTrue(loadedTodo instanceof Todo);
        assertEquals("Complete homework", loadedTodo.getDescription());
        assertTrue(loadedTodo.isDone());

        // Check Deadline task
        Task loadedDeadline = loadedTasks.get(1);
        assertTrue(loadedDeadline instanceof Deadline);
        assertEquals("Submit assignment", loadedDeadline.getDescription());
        assertTrue(loadedDeadline.isDone()); // Deadline is marked as done
        assertEquals("19-09-2024 0900hrs", ((Deadline) loadedDeadline).getDeadlineOfTask());

        // Check Event task
        Task loadedEvent = loadedTasks.get(2);
        assertTrue(loadedEvent instanceof Event);
        assertEquals("Meeting", loadedEvent.getDescription());
        assertTrue(loadedEvent.isDone()); // Event is marked as done
        assertEquals("21-09-2024 1000hrs", ((Event) loadedEvent).getStartOfEvent());
        assertEquals("21-09-2024 1200hrs", ((Event) loadedEvent).getEndOfEvent());
    }
}
