package waterfall;

import org.junit.jupiter.api.*;
import waterfall.task.Deadline;
import waterfall.task.Event;
import waterfall.task.Task;
import waterfall.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private static final String TEST_FILE_PATH = "testData/Tasks.txt";
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        File f = new File(TEST_FILE_PATH);
        if (f.exists()) {
            f.delete();
        }
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        File f = new File(TEST_FILE_PATH);
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    void updateTask_shouldOverwriteFileWithNewTasks() throws IOException, WaterfallException {
        Task todo = new ToDo("Read book");
        Task deadline = new Deadline("Submit report", "2024-08-31 1200");
        storage.addTask(todo);

        storage.updateTask(List.of(deadline));
        List<Task> tasks = storage.load();
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        assertEquals("Submit report", tasks.get(0).getTitle());
    }

    @Test
    void load_shouldLoadTasksFromFile() throws IOException, WaterfallException {
        Task todo = new ToDo("Read book");
        Task deadline = new Deadline("Submit report", "2024-08-31 0600");
        Task event = new Event("Team meeting", "2024-08-08 1200", "2024-08-09 1200");

        storage.addTask(todo);
        storage.addTask(deadline);
        storage.addTask(event);

        List<Task> tasks = storage.load();
        assertEquals(3, tasks.size());
        assertInstanceOf(ToDo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertInstanceOf(Event.class, tasks.get(2));
    }

    @Test
    void load_shouldThrowExceptionForCorruptedData() throws IOException {
        File file = new File(TEST_FILE_PATH);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("Invalid data format\n");
        }

        WaterfallException exception = assertThrows(WaterfallException.class, storage::load);
        assertTrue(exception.getMessage().contains("Unknown task type in database"));
    }

}
