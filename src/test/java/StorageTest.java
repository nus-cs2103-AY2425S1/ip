
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class StorageTest {

    private String path = "./data/test_duke.txt";
    private Storage storage;
    private ArrayList<IndividualTask> tasksToSave;

    @BeforeEach
    public void setUp() {
        // Create a new storage instance with the test file path
        storage = new Storage(path);

        // Create some tasks to save
        tasksToSave = new ArrayList<>();
        tasksToSave.add(new ToDo("read book"));
        tasksToSave.add(new Event("project meeting", "2pm", "4pm"));
        tasksToSave.get(0).markOrUnmark("mark"); // Mark the first task as done
    }

    @Test
    public void testSaveAndLoadTasks() {

        storage.saveTasksToFile(tasksToSave);

        Storage storageReloaded = new Storage(path);
        ArrayList<IndividualTask> loadedTasks = storageReloaded.load();

        assertEquals(tasksToSave.size(), loadedTasks.size());

        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals( tasksToSave.get(i).toString(), loadedTasks.get(i).toString());
        }
    }


}
