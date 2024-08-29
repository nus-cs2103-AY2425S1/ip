package echobot;

import echobot.task.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private static final String TEST_FILE_PATH = "./data/echo-bot.txt";
    private static final String TEST_DIR_PATH = "./data/";
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        tasks.addTask(new Todo("Test Todo"));
        tasks.addTask(new Deadline("Test Deadline", "29/8/2024"));
        tasks.addTask(new Event("Test Event", "29/8/2024", "30/8/2024"));

        // Set up the file paths for testing
        Storage.ensureFileExists();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test files
        Path testFilePath = Paths.get(TEST_FILE_PATH);
        Files.deleteIfExists(testFilePath);

        Path testDirPath = Paths.get(TEST_DIR_PATH);
        if (Files.isDirectory(testDirPath)) {
            Files.delete(testDirPath);
        }
    }

    @Test
    void testEnsureFileExists() {
        // Ensure that the file exists after calling the method
        Storage.ensureFileExists();
        assertTrue(new File(TEST_FILE_PATH).exists(), "File should exist after calling ensureFileExists()");
    }

    @Test
    void testSaveTasksToFile() {
        Storage.saveTasksToFile(tasks);
        assertTrue(new File(TEST_FILE_PATH).exists(), "File should be created and saved successfully");

        // Read the file to check its contents
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String firstLine = reader.readLine();
            assertNotNull(firstLine, "File should not be empty after saving tasks");
            assertTrue(firstLine.contains("Test Todo"), "First task should be saved correctly");
        } catch (IOException e) {
            fail("Exception while reading the saved file: " + e.getMessage());
        }
    }

    @Test
    void testLoadTasksFromFile() {
        // Save tasks to file first
        Storage.saveTasksToFile(tasks);

        // Clear current tasks and load from file
        TaskList loadedTasks = new TaskList();
        Storage.loadTasksFromFile(loadedTasks);

        assertEquals(3, loadedTasks.size(), "Should load the correct number of tasks");
        assertTrue(loadedTasks.getTasks().get(0).toString().contains("Test Todo"), "The first task description should match");
    }
}