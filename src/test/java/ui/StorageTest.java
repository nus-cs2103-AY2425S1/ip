package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import task.TaskList;
import task.ToDo;
import storage.Storage;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Storage to ensure saving, loading, and file path operations are functioning correctly.
 */
class StorageTest {

    private Storage storage;
    private final String filePath = "testData.txt";

    /**
     * Sets up the environment before each test by creating a temporary file and clearing the TaskList.
     *
     * @throws Exception If there is an issue creating the file.
     */
    @BeforeEach
    void setUp() throws Exception {
        File file = new File(filePath);
        file.createNewFile();
        storage = new Storage(filePath);
        TaskList.mainTaskList.clearTasks();
    }

    /**
     * Cleans up the test environment by deleting the test file and clearing the TaskList after each test.
     *
     * @throws Exception If there is an issue deleting the file.
     */
    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(Path.of(filePath));
        TaskList.mainTaskList.clearTasks();
    }

    /**
     * Tests the clearSave method by ensuring the file is empty after clearing the saved tasks.
     *
     * @throws Exception If there is an issue writing or reading the file.
     */
    @Test
    void testClearSave() throws Exception {
        TaskList.mainTaskList.addTask(new ToDo("Task 1"));
        storage.saveData();
        storage.clearSave();
        File file = new File(filePath);
        assertEquals(0, file.length(), "File should be empty after clearSave.");
    }

    /**
     * Tests loading data from a file by verifying that the correct tasks are loaded into the TaskList.
     *
     * @throws Exception If there is an issue writing to or reading from the file.
     */
    @Test
    void testLoadData() throws Exception {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("T | 0 | Test ToDo\n");
            writer.write("E | 0 | Test Event | 09-22-2024 1500 | 09-22-2024 1600\n");
            writer.write("D | 0 | Test Deadline | 09-23-2024 1200");
        }
        storage.loadData();
        assertEquals(3, TaskList.mainTaskList.getNumTasks());
        assertEquals("Test ToDo", TaskList.mainTaskList.getTask(0).getDescription());
        assertEquals("Test Event", TaskList.mainTaskList.getTask(1).getDescription());
        assertEquals("Test Deadline", TaskList.mainTaskList.getTask(2).getDescription());
    }

    /**
     * Tests setting and getting the file path for the storage object.
     */
    @Test
    void testSetAndGetFilePath() {
        storage.setFilePath("newTestData.txt");
        assertEquals("newTestData.txt", storage.getFilePath());
    }

    /**
     * Tests that loading data from a non-existent file is handled gracefully.
     */
    @Test
    void testLoadDataFileNotFound() {
        Storage storageWithInvalidFile = new Storage("nonExistentFile.txt");
        assertDoesNotThrow(storageWithInvalidFile::loadData, "Should handle file not found gracefully.");
    }
}

