package Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import Task.TaskList;
import Task.ToDo;
import Task.Deadline;
import Task.Event;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private Storage storage;
    private String tempFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("tempTaskFile", ".txt");
        tempFilePath = tempFile.getAbsolutePath();
        storage = new Storage(tempFilePath);

        // Reset the TaskList
        while (TaskList.mainTaskList.getNumTasks() > 0) {
            TaskList.mainTaskList.deleteTask(0);
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file after each test
        new File(tempFilePath).delete();
    }

    @Test
    public void testSaveAndLoadDataWithTasks() {
        // Add tasks to the TaskList
        new ToDo("Task 1");
        new Deadline("Task 2", "01-01-2024 1200");
        new Event("Task 3", "01-01-2024 1200", "01-01-2024 1300");

        // Save tasks to the file
        storage.saveData();

        // Clear the TaskList
        while (TaskList.mainTaskList.getNumTasks() > 0) {
            TaskList.mainTaskList.deleteTask(0);
        }

        // Load tasks from the file
        storage.loadData();

        // Verify that tasks are correctly loaded
        assertEquals(3, TaskList.mainTaskList.getNumTasks());
        assertEquals("Task 1", TaskList.mainTaskList.getTask(0).getDescription());
        assertEquals("Task 2", TaskList.mainTaskList.getTask(1).getDescription());
        assertEquals("Task 3", TaskList.mainTaskList.getTask(2).getDescription());
    }

    @Test
    public void testSaveAndLoadDataWithNoTasks() {
        // Save when no tasks are present
        storage.saveData();

        // Clear the TaskList
        while (TaskList.mainTaskList.getNumTasks() > 0) {
            TaskList.mainTaskList.deleteTask(0);
        }

        // Load tasks from the file
        storage.loadData();

        // Verify that no tasks are loaded
        assertEquals(0, TaskList.mainTaskList.getNumTasks());
    }

    @Test
    public void testLoadDataFromNonExistentFile() {
        // Set a non-existent file path
        storage.setFilePath("nonExistentFile.txt");

        // Attempt to load data from a non-existent file
        storage.loadData();

        // Verify that no tasks are loaded and no exceptions occur
        assertEquals(0, TaskList.mainTaskList.getNumTasks());
    }
}

