package elysia.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import elysia.exception.InvalidFileFormatException;
import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.task.ToDos;

/**
 * Unit tests for the Storage class. Tests the functionality of adding tasks to storage and saving/loading tasks.
 */
public class StorageTest {

    private Storage storage;
    private File tempFile;
    private ArrayList<Task> tasks;

    @TempDir
    Path tempDir;

    /**
     * Sets up the test environment. Initializes the Storage instance and prepares an empty task list before each test.
     */
    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
        storage = new Storage(tasks);
    }

    /**
     * Cleans up the test environment. Deletes the temporary file after each test to avoid leaving unnecessary files.
     */
    @AfterEach
    public void cleanUp() {
        if ((tempFile != null) && tempFile.exists()) {
            try {
                Files.delete(tempFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Failed to clean up the tempFile", e);
            }
        }
    }

    /**
     * Tests adding a ToDos task from a file. Verifies that the task is correctly parsed and added to the task list.
     *
     * @throws FileNotFoundException If the test file cannot be found.
     */
    @Test
    public void storage_testAddToDos() throws FileNotFoundException, InvalidFileFormatException {
        storage.scanFileContents(createTestFile("T | 0 | borrow book\n"));

        assertEquals(1, tasks.size());
        assertInstanceOf(ToDos.class, tasks.get(0));
        assertEquals("borrow book", tasks.get(0).getDescription());
        assertEquals("[ ]", tasks.get(0).getStatusIcon());
    }

    /**
     * Tests adding a Deadline task from a file. Verifies that the task is correctly parsed and added to the task list.
     *
     * @throws FileNotFoundException If the test file cannot be found.
     */
    @Test
    public void storage_testAddDeadline() throws FileNotFoundException, InvalidFileFormatException {
        storage.scanFileContents(createTestFile("D | 1 | return book | 2024-09-23\n"));

        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        assertEquals("return book", tasks.get(0).getDescription());
        assertEquals("[X]", tasks.get(0).getStatusIcon());
    }

    /**
     * Tests saving tasks to a file and checking the file's content. Verifies that the tasks are correctly saved and
     * that the file content matches the expected format.
     *
     * @throws IOException If there is an error writing to or reading from the file.
     */
    @Test
    public void testHandleExit() throws IOException {
        tasks.add(new ToDos("read book"));
        tasks.add(new Deadline("return book", LocalDate.of(2024, 9, 2)));
        tasks.add(new Event("project meeting",
                LocalDateTime.of(2024, 9, 23, 18, 0),
                LocalDateTime.of(2024, 9, 23, 20, 0)));

        //append the given string to tempDir
        Path filePath = tempDir.resolve("tasks.txt");

        storage.saveFile(tempDir.toString(), "tasks.txt", filePath.toString());

        //check the file's content
        String content = Files.readString(filePath);
        System.out.println(content);
        assertTrue(content.contains("T | 0 | read book"));
        assertTrue(content.contains("D | 0 | return book | 2024-09-02"));
        assertTrue(content.contains("E | 0 | project meeting | 2024-09-23T18:00 | 2024-09-23T20:00"));
    }

    /**
     * Creates a temporary test file with the given content. Writes the specified content to a temporary file and
     * returns its absolute path.
     *
     * @param s The content to write to the file.
     * @return The absolute path of the created test file.
     */
    private String createTestFile(String s) {
        try {
            tempFile = Files.createTempFile(tempDir, "test", ".txt").toFile();
            FileWriter writer = new FileWriter(tempFile);
            writer.write(s);
            writer.close();

            tempFile = Files.createTempFile(tempDir, "test", ".txt").toFile();
            Files.write(tempFile.toPath(), s.getBytes());
            return tempFile.getAbsolutePath();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create test file", e);
        }
    }
}
