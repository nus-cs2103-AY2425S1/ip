package storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageTest {

    private FileStorage fileStorage;
    private final String testDirPath = "test_storage";
    private final String testFilePath = testDirPath + "/storage.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Clean up previous test files if any
        Files.deleteIfExists(Paths.get(testFilePath));
        Files.createDirectories(Paths.get(testDirPath));

        fileStorage = new FileStorage(testDirPath);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up test files
        Files.deleteIfExists(Paths.get(testFilePath));
        Files.deleteIfExists(Paths.get(testDirPath));

    }

    @Test
    void testConstructorCreatesFile() {
        File file = new File(testFilePath);
        assertTrue(file.exists());
    }

    @Test
    void testStoreElement() {
        String element = "Task 1";
        List<String> result = fileStorage.store(element);
        assertNotNull(result);
        assertTrue(result.contains(element));

        // Verify the file content
        List<String> fileContent = fileStorage.load();
        assertEquals(1, fileContent.size());
        assertEquals(element, fileContent.get(0));
    }

    @Test
    void testLoadFromFile() {
        // Simulate storing elements directly in the file
        try {
            Files.write(Paths.get(testFilePath), "Task 1\nTask 2\n".getBytes());
        } catch (IOException e) {
            fail("Failed to write to the test file.");
        }

        List<String> result = fileStorage.load();
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0));
        assertEquals("Task 2", result.get(1));
    }

    @Test
    void testUpdateFile() {
        List<String> newData = new ArrayList<>();
        newData.add("Updated Task 1");
        newData.add("Updated Task 2");

        List<String> result = fileStorage.update(newData);
        assertEquals(2, result.size());
        assertEquals("Updated Task 1", result.get(0));
        assertEquals("Updated Task 2", result.get(1));

        // Verify the file content
        List<String> fileContent = fileStorage.load();
        assertEquals(2, fileContent.size());
        assertEquals("Updated Task 1", fileContent.get(0));
        assertEquals("Updated Task 2", fileContent.get(1));
    }
}

