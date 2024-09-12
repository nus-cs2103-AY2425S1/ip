package taskalyn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Verifies that database reads from, writes to, and creates database properly.
 */
public class DatabaseTest {
    private static final Path TEST_FILE_PATH = Paths.get("./data/taskalyn_test.txt");
    private TaskManager taskManager;
    private Ui ui;
    private MockDatabase db;

    /**
     * Sets up ui, database, taskmanager and removes database file before each test.
     *
     * @throws IOException If an I/O error occurs during reading.
     */
    @BeforeEach
    public void setUpTest() throws IOException {
        this.ui = new Ui();
        this.db = new MockDatabase();
        this.taskManager = new TaskManager(db, ui);
        if (Files.exists(TEST_FILE_PATH)) {
            Files.delete(TEST_FILE_PATH);
        }
    }

    /**
     * Deletes database file after each test.
     *
     * @throws IOException If an I/O error occurs during reading.
     */
    @AfterEach
    public void tearDownTest() throws IOException {
        if (Files.exists(TEST_FILE_PATH)) {
            Files.delete(TEST_FILE_PATH);
        }
    }

    @Test
    public void createDatabase_whenNoDatabaseFileExists() {
        assertFalse(Files.exists(TEST_FILE_PATH), "File should not exist before database is initialized");
        db.createDatabase();
        assertTrue(Files.exists(TEST_FILE_PATH), "File should exist after database is initialized");
    }

    /**
     * Verifies that tasks written to database are described correctly in database file.
     */
    @Test
    public void writeToDatabase_multipleTasksTypes_writtenInExpectedFormat() throws IOException {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        List<String> taskInfo = db.readFromDatabase();
        assertEquals("T | 0 | go to school", taskInfo.get(0));
        assertEquals("D | 0 | hw | 11-09-2024 1300", taskInfo.get(1));
        assertEquals("E | 0 | party | 11-09-2024 1800 | 11-09-2024 2000", taskInfo.get(2));
    }

    @Test
    public void updateDatabase_afterTaskOperations_updatedSuccessfully() throws IOException {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));

        taskManager.addTask(new TodoTask("do homework", false));

        taskManager.markTaskAsComplete(1);
        taskManager.markTaskAsComplete(2);
        taskManager.markTaskAsComplete(3);
        taskManager.markTaskAsIncomplete(2);
        taskManager.deleteTask(4);

        List<String> taskInfo = db.readFromDatabase();

        assertEquals("T | 1 | go to school", taskInfo.get(0));
        assertEquals("D | 0 | hw | 11-09-2024 1300", taskInfo.get(1));
        assertEquals("E | 1 | party | 11-09-2024 1800 | 11-09-2024 2000", taskInfo.get(2));
    }

    @Test
    public void readDatabase_readInExpectedFormat() throws IOException {
        taskManager.addTask(new TodoTask("go to school", false));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));

        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        List<String> tasks = db.readFromDatabase();
        assertEquals("T | 0 | go to school", tasks.get(0));
        assertEquals("D | 0 | hw | 11-09-2024 1300", tasks.get(1));
        assertEquals("E | 0 | party | 11-09-2024 1800 | 11-09-2024 2000", tasks.get(2));
    }

    @Test
    public void readDatabase_newCreatedDatabase_containsNoTasks() throws IOException {
        db.createDatabase();

        List<String> tasks = db.readFromDatabase();
        assertEquals(0, tasks.size(), "A newly created database should have no tasks");
    }
}
