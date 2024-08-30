package wenjieBot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wenjieBot.exceptions.NoFileException;
import wenjieBot.tasks.Deadline;
import wenjieBot.tasks.Event;
import wenjieBot.tasks.Task;
import wenjieBot.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private static final String TEST_FILE_PATH = "testStorage.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file to use for testing
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test file after each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testReadTasksFromFile() throws IOException, NoFileException {
        String fileContent = "T | 1 | read book\n" +
                "D | 0 | return book /by 2/12/2019 1800\n" +
                "E | 1 | project meeting /from: Mon 2pm /to: 4pm\n";

        Files.write(new File(TEST_FILE_PATH).toPath(), fileContent.getBytes());

        storage.readTasks();

        ArrayList<Task> tasks = storage.load();
        assertEquals(3, tasks.size());

        assertTrue(tasks.get(0) instanceof ToDo);
        assertTrue(tasks.get(1) instanceof Deadline);
        assertTrue(tasks.get(2) instanceof Event);

        assertTrue(tasks.get(0).isDone());
        assertFalse(tasks.get(1).isDone());
        assertTrue(tasks.get(2).isDone());
    }

    @Test
    public void testWriteTasksToFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Deadline("submit assignment ", "2/12/2019 1800"));

        tasks.add(new Event("project meeting ", "Mon 2pm ", "4pm"));
        tasks.get(0).setStatusIcon(true);
        tasks.get(2).setStatusIcon(true);
        storage.load().addAll(tasks);
        System.out.println(tasks.get(2));

        storage.writeTasks();

        String expectedContent = "T | 1 | read book\n" +
                "D | 0 | submit assignment /by: 2/12/2019 1800\n" +
                "E | 1 | project meeting /from: Mon 2pm /to: 4pm\n";

        String actualContent = new String(Files.readAllBytes(new File(TEST_FILE_PATH).toPath()));
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testReadTasksFromNonExistentFile() {
        File file = new File(TEST_FILE_PATH);
        file.delete(); // Ensure the file does not exist

        assertThrows(NoFileException.class, () -> storage.readTasks());
    }

    @Test
    public void testWriteTasksToNonExistentFile() throws IOException {
        File file = new File(TEST_FILE_PATH);
        file.delete(); // Ensure the file does not exist

        // The method should handle the exception internally and not throw it
        assertDoesNotThrow(() -> storage.writeTasks());
    }
}
