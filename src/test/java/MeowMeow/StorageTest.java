package meowmeow;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class StorageTest {

    public String printFirstTask(TaskList list) {
        return list.get(0).toString();
    }
    @Test
    public void load_success() throws IOException {
        Storage s = new Storage("./data/test.txt");
        s.load("./data/test.txt");
        assertEquals("[T][ ] borrow book", printFirstTask(s.getTaskList()));
    }

    @Test
    public void load_fail_FileNotFoundExceptionThrown() {
        try {
            Storage s = new Storage("./data/fake.txt");
            s.load("./data/fake.txt");
            assertEquals("", printFirstTask(s.getTaskList()));
            fail();
        } catch (Exception e) {
            assertEquals("./data/fake.txt (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void saveData_success() throws IOException {
        // Initialize the Storage object with a test save path
        Storage s = new Storage("./data/testSave.txt");

        // Add tasks directly to the TaskList
        TaskList tasks = s.getTaskList(); // Retrieve the internal TaskList
        tasks.add(new ToDo("read book"));
        tasks.add(new Deadline("submit assignment", "2024-09-30"));

        // Save the tasks to the file
        s.saveData();

        // Create a new Storage object to load the saved data and verify the first task
        Storage loadedStorage = new Storage("./data/testSave.txt");
        loadedStorage.load("./data/testSave.txt");
        assertEquals("[T][ ] read book", printFirstTask(loadedStorage.getTaskList()));
    }

}
