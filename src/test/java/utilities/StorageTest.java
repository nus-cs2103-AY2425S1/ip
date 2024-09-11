package utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.Todo;
import utilities.Storage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    private Storage storage;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final String testFilePath = "test_data/tasks.txt";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));  // Redirect System.out to outputStream for capturing output
        storage = new Storage(testFilePath);
    }

    @Test
    public void testSaveToFile() {
        // Setup task list and save to file
        Task task = new Todo("Buy groceries");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);

        // Overwrite Storage's task list (for testing)
        storage.getTasks().add(task);

        // Save tasks to file and check output
        storage.saveToFile();
        File file = new File(testFilePath);
    }

    @Test
    public void testLoadFromFile() {
        // Write a task into the file beforehand
        Task task = new Todo("Write tests");
        storage.getTasks().add(task);
        storage.saveToFile();

        // Load tasks from file and verify contents
        storage.loadFromFile();
        ArrayList<Task> loadedTasks = storage.getTasks();

        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] Write tests", loadedTasks.get(0).toString());
    }
}

