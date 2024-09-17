package yapyap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String TEST_FILE_PATH = "./data/testTasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Path path = Paths.get(TEST_FILE_PATH);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    public void loadTasks_fileDoesNotExist_emptyListReturned() throws YapperBotException {
        // empty file
        ArrayList<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty(), "Expected empty task list when file does not exist.");
    }

    @Test
    public void loadTasks_validFile_tasksLoadedCorrectly() throws YapperBotException, IOException {
        // load task before reading
        String fileContent = "T | 1 | Read book\n" + "D | 0 | Submit report | 2024-12-01\n"
                + "E | 1 | Team meeting | 2/12/2024 0800 | 2/12/2024 0900\n";
        Files.write(Paths.get(TEST_FILE_PATH), fileContent.getBytes());

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(3, tasks.size());
        assertEquals("Read book", tasks.get(0).getDescription());
        assertTrue(tasks.get(0).getIsDone());

        assertEquals("Submit report", tasks.get(1).getDescription());
        assertTrue(tasks.get(1) instanceof Deadline);

        assertEquals("Team meeting", tasks.get(2).getDescription());
        assertTrue(tasks.get(2) instanceof Event);
    }

    @Test
    public void loadTasks_invalidTaskType_throwsException() throws IOException {
        // invalid task type
        String fileContent = "X | 1 | Invalid task type\n";
        Files.write(Paths.get(TEST_FILE_PATH), fileContent.getBytes());

        // attempt to load tasks should throw an exception
        assertThrows(YapperBotException.class, () -> storage.loadTasks());
    }

    @Test
    public void saveTasks_tasksSavedCorrectly() throws YapperBotException, IOException {
        // create a list of tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read book", true));
        tasks.add(new Deadline("Submit report", "2024-12-01", false));
        tasks.add(new Event("Team meeting", "2/12/2024 0800", "2/12/2024 0900", true));

        storage.saveTasks(tasks);

        Path path = Paths.get(TEST_FILE_PATH);
        String content = Files.readString(path);

        // content matches expected format
        String expectedContent = "T | 1 | Read book\n"
                + "D | 0 | Submit report | 2024-12-01\n"
                + "E | 1 | Team meeting | 2/12/2024 0800 | 2/12/2024 0900\n";
        assertEquals(expectedContent, content);
    }
}

