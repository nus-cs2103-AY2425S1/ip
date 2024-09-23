package utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private static final String TEST_FILE_PATH = "data/test/TaskFairyTest.txt";
    private Storage storage;

    @BeforeEach
    void setUp() {
        // Ensure clean state for each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    void testStorageFileCreation() {
        // Check that the file is created if it doesn't exist
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "Storage file should be created if it doesn't exist.");
    }

    @Test
    void testSaveToFile() throws IOException {
        // Create a task list and save it
        Todo todo = new Todo("Test Todo");
        storage.getTasks().add(todo);
        storage.saveToFile();

        // Check that the file has been written correctly
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "File should exist after saving tasks.");

        // Verify the content
        String content = new String(Files.readAllBytes(file.toPath()));
        assertTrue(content.contains("T | 0 | Test Todo"), "Todo task should be saved in correct format.");
    }

    @Test
    void testLoadFromFile() throws IOException {
        // Write test data to the file
        String fileContent = "T | 1 | Completed Todo\n" +
                "D | 0 | Incomplete Deadline | 2023-09-25T12:00\n" +
                "E | 0 | Event Description | 2023-09-25T12:00 | 2023-09-25T14:00\n";
        Files.write(new File(TEST_FILE_PATH).toPath(), fileContent.getBytes());

        // Load the tasks from the file
        storage.loadFromFile();

        // Validate loaded tasks
        ArrayList<Task> tasks = storage.getTasks();
        assertEquals(3, tasks.size(), "Should load 3 tasks from file.");

        // Verify task types and contents
        assertTrue(tasks.get(0) instanceof Todo, "First task should be a Todo.");
        assertTrue(tasks.get(1) instanceof Deadline, "Second task should be a Deadline.");
        assertTrue(tasks.get(2) instanceof Event, "Third task should be an Event.");

        // Check task details
        Todo loadedTodo = (Todo) tasks.get(0);

        assertEquals("Completed Todo", loadedTodo.getDescription(), "Todo description should match.");

        Deadline loadedDeadline = (Deadline) tasks.get(1);

        assertEquals("Incomplete Deadline", loadedDeadline.getDescription(), "Deadline description should match.");


        Event loadedEvent = (Event) tasks.get(2);

    }


    @Test
    void testSaveAndLoadComplexTaskList() throws IOException {
        // Create a complex task list
        Todo todo = new Todo("Buy groceries");
        Deadline deadline = new Deadline("Submit assignment", LocalDateTime.of(2023, 12, 15, 23, 59));
        Event event = new Event("Project meeting", LocalDateTime.of(2023, 11, 10, 9, 0), LocalDateTime.of(2023, 11, 10, 11, 0));

        storage.getTasks().add(todo);
        storage.getTasks().add(deadline);
        storage.getTasks().add(event);

        // Save the task list to the file
        storage.saveToFile();

        // Clear the current task list and reload from file
        storage.getTasks().clear();
        storage.loadFromFile();

        // Validate the reloaded tasks
        ArrayList<Task> tasks = storage.getTasks();
        assertEquals(3, tasks.size(), "Should load 3 tasks after saving and loading.");

        // Ensure tasks are of correct types and details are preserved
        assertTrue(tasks.get(0) instanceof Todo, "First task should be a Todo.");
        assertTrue(tasks.get(1) instanceof Deadline, "Second task should be a Deadline.");
        assertTrue(tasks.get(2) instanceof Event, "Third task should be an Event.");
    }
}
