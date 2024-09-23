package pebble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the Storage class.
 */
public class StorageTest {

    private final String testFilePath = "testData/tasks.txt";
    private Storage storage;

    /**
     * Set up method to initialize the Storage object and ensure the test directory exists.
     */
    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
        new File("testData").mkdirs(); // Ensure the directory exists
    }

    /**
     * Clean up method to delete the test file after each test.
     */
    @AfterEach
    public void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete(); // Remove test file after tests
        }
    }

    /**
     * Test loading tasks from an empty file.
     */
    @Test
    public void testLoadTasksFromEmptyFile() throws IOException {
        FileWriter writer = new FileWriter(testFilePath);
        writer.write(""); // Empty file
        writer.close();

        ArrayList<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty(), "Expected empty tasks list when file is empty.");
    }

    /**
     * Test saving and loading ToDo tasks.
     */
    @Test
    public void testSaveAndLoadToDoTasks() throws IOException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new ToDo("Read a book"));
        tasksToSave.add(new ToDo("Write a report"));

        // Save tasks to file
        storage.saveTasks(tasksToSave);

        // Load tasks from file and compare
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(2, loadedTasks.size());
        assertEquals("[T][ ] Read a book", loadedTasks.get(0).toString());
        assertEquals("[T][ ] Write a report", loadedTasks.get(1).toString());
    }

    /**
     * Test saving and loading Deadline tasks.
     */
    @Test
    public void testSaveAndLoadDeadlineTasks() throws IOException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Deadline("Submit assignment", "2024-10-01"));
        tasksToSave.add(new Deadline("Pay bills", "2024-10-05"));

        // Save tasks to file
        storage.saveTasks(tasksToSave);

        // Load tasks from file and compare
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(2, loadedTasks.size());
        assertEquals("[D][ ] Submit assignment (by: Oct 01 2024)", loadedTasks.get(0).toString());
        assertEquals("[D][ ] Pay bills (by: Oct 05 2024)", loadedTasks.get(1).toString());
    }

    /**
     * Test saving and loading Event tasks.
     */
    @Test
    public void testSaveAndLoadEventTasks() throws IOException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Event("Conference", "2024-09-10", "2024-09-12"));
        tasksToSave.add(new Event("Wedding", "2024-12-15", "2024-12-16"));

        // Save tasks to file
        storage.saveTasks(tasksToSave);

        // Load tasks from file and compare
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(2, loadedTasks.size());
        assertEquals("[E][ ] Conference (from: Sep 10 2024 to: Sep 12 2024)", loadedTasks.get(0).toString());
        assertEquals("[E][ ] Wedding (from: Dec 15 2024 to: Dec 16 2024)", loadedTasks.get(1).toString());
    }

    /**
     * Test loading tasks when the file contains invalid tasks.
     */
    @Test
    public void testLoadTasksWithInvalidTasks() throws IOException {
        // Write some invalid and valid tasks to the file
        FileWriter writer = new FileWriter(testFilePath);
        writer.write("Invalid Task Line\n");
        writer.write("[T][ ] Read a book\n"); // Valid task format
        writer.close();

        ArrayList<Task> tasks = storage.loadTasks();
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Read a book", tasks.get(0).toString());
    }

    /**
     * Test saving an empty tasks list.
     */
    @Test
    public void testSaveEmptyTasksList() throws IOException {
        ArrayList<Task> emptyTasksList = new ArrayList<>();

        // Save empty task list
        storage.saveTasks(emptyTasksList);

        // Check file content
        File file = new File(testFilePath);
        assertTrue(file.exists(), "The file should still exist after saving an empty task list.");

        // Check file is empty
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertTrue(loadedTasks.isEmpty(), "The loaded task list should be empty.");
    }

    /**
     * Test loading tasks when the file does not exist.
     */
    @Test
    public void testLoadTasksWhenFileDoesNotExist() throws IOException {
        // Ensure the file does not exist
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }

        // Load tasks and check if the file is created
        ArrayList<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty(), "The task list should be empty when the file does not exist.");
        assertTrue(file.exists(), "The file should be created when loading tasks if it does not exist.");
    }
}
