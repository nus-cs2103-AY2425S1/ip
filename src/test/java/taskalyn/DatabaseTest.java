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

public class DatabaseTest {
    private static final Path TEST_FILE_PATH = Paths.get("./data/taskalyn_test.txt");
    private TaskManager taskManager;
    private Ui ui;
    private MockDatabase db;

    @BeforeEach
    public void setUpTest() throws IOException {
        this.ui = new Ui();
        this.db = new MockDatabase();
        this.taskManager = new TaskManager(db, ui);
        if (Files.exists(TEST_FILE_PATH)) {
            Files.delete(TEST_FILE_PATH);
        }
    }

    @AfterEach
    public void tearDownTest() throws IOException {
        if (Files.exists(TEST_FILE_PATH)) {
            Files.delete(TEST_FILE_PATH);
        }
    }

    @Test
    public void testWriteToDatabase() {
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
