package bitbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StorageTest {
    private static final String TEST_FILE_PATH = "./data/TestBitbot.txt";
    private static final Path TEST_FILE = Path.of(TEST_FILE_PATH);

    private ArrayList<Task> tasks = new ArrayList<>();

    /*
    I used ChatGPT to help me with identifying and the usage of @BeforeEach & @AfterEach and
    also gave me the clue to create and delete the TestBitbot.txt file so that it is merely
    used for the purposes of testing and does not affect anything else.

    Prompt given: How to run some set of codes before and after each test method?
    Answer: In JUnit, @BeforeEach and @AfterEach are used to run code before
    and after each individual test method, respectively.
    This is particularly useful when you need to set up a known state
    (e.g., creating a file or initializing data) before running a test,
    and clean up resources (e.g., deleting files or closing connections) after the test completes.
     */
    @BeforeEach
    void setUp() throws IOException {
        // Clean up before each test by removing the test file if it exists
        Files.deleteIfExists(TEST_FILE);
        Files.createFile(TEST_FILE);

        tasks.add(new Todo("Read book"));
        tasks.add(new Deadline("Submit assignment",
                LocalDateTime.of(2024, 9, 20, 18, 0)));
        tasks.add(new Events("Attend meeting",
                LocalDateTime.of(2024, 9, 22, 10, 0),
                LocalDateTime.of(2024, 9, 22, 12, 0)));
        Storage.saveTasksToFile(tasks, TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up after each test by removing the test file
        Files.deleteIfExists(TEST_FILE);
    }

    // I used GPT to help me get the first test case.
    @Test
    void testEnsureFileExists() throws IOException {
        // Test ensuring file exists when it's already created
        Storage.ensureFileExists();
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "Test file should exist.");
    }

    @Test
    void testSaveTasks() throws IOException, FileNotFoundException {
        assertEquals(tasks.size(), 3);
    }

    @Test
    void testReadTodoTaskDescription_success() throws IOException, FileNotFoundException {
        assertInstanceOf(Todo.class, tasks.get(0));
        assertEquals("Read book", tasks.get(0).taskDescription);
    }

    @Test
    void testReadDeadlineTaskDescription_success() throws IOException {
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertEquals("Submit assignment", tasks.get(1).taskDescription);
    }

    @Test
    void testReadEventTaskDescription_success() throws IOException, FileNotFoundException {
        assertInstanceOf(Events.class, tasks.get(2));
        assertEquals("Attend meeting", tasks.get(2).taskDescription);
    }

    @Test
    void testReadDeadlineTaskFinalString_success() throws IOException, FileNotFoundException {
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertEquals("[D][ ] Submit assignment (by: Sep 20 2024 18:00)",
                tasks.get(1).finalString());
    }
    @Test
    void testReadEventsTaskFinalString_success() throws IOException, FileNotFoundException {
        assertInstanceOf(Events.class, tasks.get(2));
        assertEquals("[E][ ] Attend meeting (from: Sep 22 2024 10:00 to: Sep 22 2024 12:00)",
                tasks.get(2).finalString());
    }

}
