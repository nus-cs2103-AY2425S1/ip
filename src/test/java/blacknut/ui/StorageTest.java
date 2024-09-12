package blacknut.ui;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testLoadTasksFromFile_noFile() {
        // Create a Storage instance with a non-existent file
        Storage storage = new Storage("non_existent_file.txt");

        // Attempt to load tasks from the non-existent file
        ArrayList<Task> tasks = storage.loadTasksFromFile();

        // Verify that an empty list is returned
        assertTrue(tasks.isEmpty(), "Task list should be empty if the file doesn't exist");
    }

    @Test
    public void testLoadTasksFromFile_withTasks() throws IOException {
        // Set up a temporary file with some tasks
        String filePath = "test_tasks.txt";
        File file = new File(filePath);
        file.createNewFile();

        // Create a Storage instance and save tasks to the file
        Storage storage = new Storage(filePath);
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Test task 1"));
        tasksToSave.add(new Deadline("Test task 2", "2024-12-31 23:59"));
        tasksToSave.add(new Event("Test task 3", "2024-01-01 09:00", "2024-01-01 17:00"));
        storage.saveTasksToFile(tasksToSave);

        // Load the tasks from the file
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();

        // Verify that the loaded tasks match the saved tasks
        assertEquals(3, loadedTasks.size(), "Should load three tasks");
        assertEquals("[T][ ] Test task 1", loadedTasks.get(0).toString());
        assertEquals("[D][ ] Test task 2 (by: Dec 31 2024, 23:59)", loadedTasks.get(1).toString());
        assertEquals("[E][ ] Test task 3 (from: Jan 01 2024, 09:00 to: Jan 01 2024, 17:00)", loadedTasks.get(2).toString());

        // Clean up
        file.delete();
    }

    @Test
    public void testSaveTasksToFile() throws IOException {
        // Set up a temporary file
        String filePath = "test_save_tasks.txt";
        File file = new File(filePath);
        file.createNewFile();

        // Create a Storage instance and save tasks to the file
        Storage storage = new Storage(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test save task 1"));
        tasks.add(new Deadline("Test save task 2", "2024-12-31 23:59"));
        tasks.add(new Event("Test save task 3", "2024-01-01 09:00", "2024-01-01 17:00"));
        storage.saveTasksToFile(tasks);

        // Load the tasks from the file to verify they were saved correctly
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();

        // Verify that the loaded tasks match the saved tasks
        assertEquals(3, loadedTasks.size(), "Should load three tasks");
        assertEquals("[T][ ] Test save task 1", loadedTasks.get(0).toString());
        assertEquals("[D][ ] Test save task 2 (by: Dec 31 2024, 23:59)", loadedTasks.get(1).toString());
        assertEquals("[E][ ] Test save task 3 (from: Jan 01 2024, 09:00 to: Jan 01 2024, 17:00)", loadedTasks.get(2).toString());

        // Clean up
        file.delete();
    }
}
