package taskalyn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Verifies that tasks written to database are described correctly in database file.
     */
    @Test
    public void writeToDatabase_multipleTasks_writtenSuccessfully() {
        // Arrange
        List<Task> tasks = Arrays.asList(
                new TodoTask("Test Task 1", false),
                new DeadlineTask("Test Task 2", "2024-08-05 2300", false),
                new EventTask("Test Task 3", "tdy", "tmr", false)
                );

        // Act

        // Assert
    }

}
