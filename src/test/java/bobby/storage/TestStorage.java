package bobby.storage;

// Static imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Java standard library imports
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

// Special imports (JUnit library imports)
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Project-specific imports
import bobby.exceptions.InvalidTaskException;
import bobby.tasklist.TaskList;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;



/**
 * Test class for the Storage class.
 * This class contains unit tests for the methods in the Storage class.
 */
public class TestStorage {
    private static final String TEST_FILE_PATH = "./src/main/data/test_tasks.txt";
    private Storage storage;
    private TaskList taskList;
    // Helper methods
    private boolean isFileEmpty(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.length() == 0;
    }

    private void writeToFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }

    @BeforeEach
    void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        taskList = new TaskList();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    void testSaveTasks_emptyTaskList() throws InvalidTaskException {
        taskList.add(new Todo("Test"));
        storage.saveTasks(taskList);
        assertTrue(new File(TEST_FILE_PATH).exists(), "File should exist after saving tasks.");
    }

    @Test
    void testSaveTasks_nonEmptyTaskList() throws IOException, InvalidTaskException {
        taskList.add(new Todo("Read book"));
        taskList.add(new Deadline("Submit report", LocalDate.of(2024, 12, 12)));
        taskList.add(new Event("Conference", LocalDate.of(2024, 12, 15), LocalDate.of(2024, 12, 16)));

        storage.saveTasks(taskList);

        String content = new String(Files.readAllBytes(Paths.get(TEST_FILE_PATH)));
        String expectedContent = "T | false | Read book\n"
                + "D | false | Submit report | 2024-12-12\n"
                + "E | false | Conference | 2024-12-15 | 2024-12-16\n";

        content = normalizeLineEndings(content);
        expectedContent = normalizeLineEndings(expectedContent);

        assertEquals(expectedContent, content, "File content should match the expected output.");
    }

    private String normalizeLineEndings(String input) {
        return input.replace("\r\n", "\n").replace("\r", "\n");
    }

    @Test
    void testLoadTasks_emptyFile() {
        TaskList loadedTasks = storage.loadTasks();
        assertTrue(loadedTasks.getTasks().isEmpty(), "Loaded task list should be empty for an empty file.");
    }

    @Test
    void testLoadTasks_nonEmptyFile() throws IOException {
        String content = "T | false | Read book\n"
                + "D | false | Submit report | 2024-12-12\n"
                + "E | false | Conference | 2024-12-15 | 2024-12-16\n";
        writeToFile(TEST_FILE_PATH, content);

        TaskList loadedTasks = storage.loadTasks();

        assertEquals(3, loadedTasks.getTasks().size(), "Loaded task list should contain 3 tasks.");

        Task task1 = loadedTasks.getTasks().get(0);
        assertTrue(task1 instanceof Todo, "First task should be a Todo.");
        assertEquals("[T][ ] Read book", task1.toString());

        Task task2 = loadedTasks.getTasks().get(1);
        assertTrue(task2 instanceof Deadline, "Second task should be a Deadline.");
        assertEquals("[D][ ] Submit report (by: Dec 12 2024)", task2.toString());

        Task task3 = loadedTasks.getTasks().get(2);
        assertTrue(task3 instanceof Event, "Third task should be an Event.");
        assertEquals("[E][ ] Conference (from: Dec 15 2024 to: Dec 16 2024)", task3.toString());
    }


}
