package storage;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDoTask;

import java.util.ArrayList;

public class StorageTest {
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = "data/prince.Prince.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(DIR_PATH, FILE_PATH);
        storage.createListFile();
    }

    @Test
    public void testLoadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDoTask("Buy milk"));
        storage.pushTasksToFile(tasks);

        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        assertEquals(1, loadedTasks.size());
        assertEquals("Buy milk", loadedTasks.get(0).getDescription());
    }
}
