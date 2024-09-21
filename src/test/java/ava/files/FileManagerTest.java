package ava.files;

import ava.task.Task;
import ava.task.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FileManagerTest {


    private FileManager fileManager;
    private static final String TEST_PATH = "./test_data/tasks.txt";

    @BeforeEach
    void setUp() {
        // Create a new FileManager instance with a test path
        fileManager = new FileManager(TEST_PATH);
    }

    @AfterEach
    void tearDown() {
        // Delete the test file
        File file = new File(TEST_PATH);
        if (file.exists()) {
            file.delete();
        }
        fileManager = null;
    }

    @Test
    void getTasks() {
        // Prepare test data
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Todo("Task 1"));
        expectedTasks.add(new Todo("Task 2"));

        // Write test data to file
        try (PrintWriter writer = new PrintWriter(new File(TEST_PATH))) {
            for (Task task : expectedTasks) {
                writer.println(DataManager.serialize(task));
            }
        } catch (Exception e) {
            fail("Failed to write test data to file");
        }

        // Read tasks from file
        List<Task> actualTasks = fileManager.getTasks();

        // Verify the tasks
        assertEquals(expectedTasks.size(), actualTasks.size());
        for (int i = 0; i < expectedTasks.size(); i++) {
            assertEquals(expectedTasks.get(i).getTitle(), actualTasks.get(i).getTitle());
        }
    }

    @Test
    void writeTasks() {
        // Prepare test data
        List<Task> tasksToWrite = new ArrayList<>();
        tasksToWrite.add(new Todo("Task 1"));
        tasksToWrite.add(new Todo("Task 2"));

        // Write tasks to file
        fileManager.writeTasks(tasksToWrite);

        // Read tasks from file
        List<Task> actualTasks = fileManager.getTasks();

        // Verify the tasks
        assertEquals(tasksToWrite.size(), actualTasks.size());
        for (int i = 0; i < tasksToWrite.size(); i++) {
            assertEquals(tasksToWrite.get(i).getTitle(), actualTasks.get(i).getTitle());
        }
    }
}