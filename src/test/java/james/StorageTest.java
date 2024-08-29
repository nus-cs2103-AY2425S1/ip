package james;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        testFile = File.createTempFile("james_test", ".txt");
        testFile.deleteOnExit(); // Ensure the file is deleted after tests
        storage = new Storage(testFile.getPath());
    }

    @Test
    public void testSaveAndLoadTasks() throws MissingDescriptionException {
        ArrayList<Task> taskList = new ArrayList<>();
        Task todo = new Todo("Read a book", false);
        Task deadline = new Deadline("Submit assignment", false, LocalDateTime.of(2024, 8, 31, 23, 59));
        Task event = new Event("Project meeting", false, LocalDateTime.of(2024, 8, 30, 10, 0), LocalDateTime.of(2024, 8, 30, 12, 0));

        taskList.add(todo);
        taskList.add(deadline);
        taskList.add(event);

        // Save tasks to file
        storage.saveTasks(taskList);

        // Create a new Storage instance to load the saved tasks
        Storage newStorage = new Storage(testFile.getPath());
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        newStorage.loadSavedData(loadedTaskList);

        // Verify that the tasks were saved and loaded correctly
        assertEquals(3, loadedTaskList.size(), "Task list size should be 3 after loading saved data.");

        assertEquals(todo.printTask(), loadedTaskList.get(0).printTask(), "The first task should be the same.");
        assertEquals(deadline.printTask(), loadedTaskList.get(1).printTask(), "The second task should be the same.");
        assertEquals(event.printTask(), loadedTaskList.get(2).printTask(), "The third task should be the same.");
    }


}
