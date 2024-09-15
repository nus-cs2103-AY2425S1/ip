package ynch.ui; /**
 * Loads and saves tasks into a file
 *
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StorageTest {
    
    private Storage storage;
    private final String testFilePath = "testStorage.txt";

    @BeforeEach
    void setUp() {
        // Initialize the Storage object with a test file path
        storage = new Storage(".", testFilePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test file after each test
        Files.deleteIfExists(Paths.get(testFilePath));
    }

    @Test
    void testLoadEmptyFile() {
        // Load from an empty file
        TaskList taskList = storage.load();
        assertNotNull(taskList);
        assertTrue(taskList.isEmpty(), "Task list should be empty when loading from an empty file");
    }

    @Test
    void testSaveAndLoadTasks() throws IOException {
        // Create a TaskList and save it
        TaskList taskListToSave = new TaskList();
        taskListToSave.add(new Todo(0, "Test Todo")); // Assuming Todo constructor takes (status, description)
        storage.save(taskListToSave);

        // Load the tasks from the file
        TaskList loadedTaskList = storage.load();
        assertNotNull(loadedTaskList);
        assertEquals(1, loadedTaskList.getSize(), "Task list should contain one task after loading");
        assertEquals("Test Todo", loadedTaskList.getTasks().get(0).getDescription(), "Loaded task description should match");
    }

    @Test
    void testStringToTask() {
        // Test the stringToTask method
        Task task = storage.stringToTask("T1/Test Todo");
        assertNotNull(task);
        assertInstanceOf(Todo.class, task, "Task should be an instance of Todo");
        assertEquals("Test Todo", task.getDescription(), "Task description should match");

        task = storage.stringToTask("D0/Deadline Task/2023-12-31");
        assertNotNull(task);
        assertInstanceOf(Deadline.class, task, "Task should be an instance of Deadline");
        assertEquals("Deadline Task", task.getDescription(), "Deadline task description should match");

        task = storage.stringToTask("E1/Event Task/2023-12-31/2024-01-01");
        assertNotNull(task);
        assertInstanceOf(Event.class, task, "Task should be an instance of Event");
        assertEquals("Event Task", task.getDescription(), "Event task description should match");
    }
}
