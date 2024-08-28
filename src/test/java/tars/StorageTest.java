package tars;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class StorageTest {

    private Storage storage;
    private File tempFile;

    @Before
    public void setUp() throws Exception {
        tempFile = File.createTempFile("tars", ".txt");
        storage = new Storage(tempFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testSaveAndLoadTasks() throws TarsException {
        // Create some tasks
        Todo todo = new Todo("Read book", false);
        Deadline deadline = new Deadline("Return book", false, "2024-08-27");
        Event event = new Event("Meeting", false, "2024-08-27", "2024-08-28");

        // Save tasks to file
        storage.saveTasks(List.of(todo, deadline, event));

        // Load tasks from file
        List<Task> tasks = storage.loadTasks();

        // Verify loaded tasks
        assertEquals(3, tasks.size());
        assertTrue(tasks.get(0) instanceof Todo);
        assertTrue(tasks.get(1) instanceof Deadline);
        assertTrue(tasks.get(2) instanceof Event);

        // Check individual task details
        assertEquals("[T][ ] Read book", tasks.get(0).toString());
        assertEquals("[D][ ] Return book (by: 27 Aug 2024)", tasks.get(1).toString());
        assertEquals("[E][ ] Meeting (from: 27 Aug 2024, 00:00 to: 28 Aug 2024, 00:00)", tasks.get(2).toString());
    }

    @Test
    public void testLoadEmptyFile() throws TarsException {
        // Load tasks from an empty file
        List<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty());
    }
}
