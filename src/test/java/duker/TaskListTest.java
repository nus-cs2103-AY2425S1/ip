package duker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private final Path DATA_DIRECTORY = Paths.get("data");
    private final File TEST_FILE = new File(DATA_DIRECTORY.toFile(), "test.txt");
    private final PrintStream ORIGINAL_OUT = System.out;
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws IOException {
        if (!Files.exists(DATA_DIRECTORY)) {
            Files.createDirectories(DATA_DIRECTORY);
        }
        if (!TEST_FILE.exists()) {
            TEST_FILE.createNewFile();
        }
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(ORIGINAL_OUT);
        if (TEST_FILE.exists()) {
            TEST_FILE.delete();
        }
    }

    @Test
    public void testMark_success() {
        TaskList taskList = new TaskList();
        taskList.getTaskList().add(new Todo("Test Task"));
        int taskIndex = 1;
        taskList.mark(taskIndex, new Storage("data/", "test.txt"));
        assertTrue(taskList.getTask(taskIndex - 1).isDone());
        String expectedOutput = "Nice! I've marked this task as done:" + System.lineSeparator() +
                taskList.getTask(taskIndex - 1).toString() + System.lineSeparator();
        assertEquals(expectedOutput, OUT_CONTENT.toString());
    }

    @Test
    public void testFindTasks_keywordPresent_success() {
        TaskList taskList = new TaskList();
        taskList.getTaskList().add(new Todo("Test Task"));
        ArrayList<Task> tasksFound = taskList.findTasks("Test");
        assertEquals(1, tasksFound.size());
        assertEquals("Test Task", tasksFound.get(0).getDescription());
    }

    @Test
    public void testFindTasks_keywordAbsent_success() {
        TaskList taskList = new TaskList();
        ArrayList<Task> tasksFound = taskList.findTasks("Test");
        assertEquals(0, tasksFound.size());
    }

    @Test
    public void testFindTasks_multipleMatches_success() {
        TaskList taskList = new TaskList();
        taskList.getTaskList().add(new Todo("Test Task 1"));
        taskList.getTaskList().add(new Todo("Test Task 2"));
        ArrayList<Task> tasksFound = taskList.findTasks("Test");
        assertEquals(2, tasksFound.size());
        assertEquals("Test Task 1", tasksFound.get(0).getDescription());
        assertEquals("Test Task 2", tasksFound.get(1).getDescription());
    }

    @Test
    public void testFindTasks_correctMatches_success() {
        TaskList taskList = new TaskList();
        taskList.getTaskList().add(new Todo("Test Task 1"));
        Task dummy = new Todo("Dummy Task");
        taskList.getTaskList().add(dummy);
        taskList.getTaskList().add(new Todo("Test Task 2"));
        ArrayList<Task> tasksFound = taskList.findTasks("Test");
        assertEquals(2, tasksFound.size());
        assertEquals("Test Task 1", tasksFound.get(0).getDescription());
        assertEquals("Test Task 2", tasksFound.get(1).getDescription());
        assertFalse(tasksFound.contains(dummy));
    }

    @Test
    public void testFindTasks_nullKeyword_assertionFailed() {
        TaskList taskList = new TaskList();
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            taskList.findTasks(null);
        });
        assertEquals("Keyword should not be null or empty", exception.getMessage());
    }

    @Test
    public void testFindTasks_emptyKeyword_assertionFailed() {
        TaskList taskList = new TaskList();
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            taskList.findTasks("");
        });
        assertEquals("Keyword should not be null or empty", exception.getMessage());
    }
}

