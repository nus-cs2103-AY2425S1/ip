package myapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myapp.exceptions.BingBongException;
import myapp.exceptions.UnknownTaskTypeException;
import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.FixedDuration;
import myapp.task.Task;
import myapp.task.TaskList;
import myapp.task.ToDo;


class StorageTest {

    private static final String TEST_FILE_PATH = "test_storage.txt";
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void load_validTasksInFile_returnsCorrectTaskList() throws Exception {
        writeToFile(
                "T | 0 | Read a book\n"
                        + "D | 1 | Submit assignment | 20/09/2023 2359\n"
                        + "E | 0 | Birthday party | 20/09/2023 1800 | 20/09/2023 2100\n"
                        + "F | 1 | Exercise | 2"
        );
        List<Task> tasks = storage.load();
        assertEquals(4, tasks.size(), "Task list size should be 4.");

        // Verify each task's details
        assertTrue(tasks.get(0) instanceof ToDo, "First task should be a ToDo.");
        assertEquals("Read a book", tasks.get(0).getDescription(), "First task description mismatch.");
        assertEquals(" ", tasks.get(0).getStatusIcon(), "First task should not be marked as done.");

        assertTrue(tasks.get(1) instanceof Deadline, "Second task should be a Deadline.");
        assertEquals("X", tasks.get(1).getStatusIcon(), "Second task should be marked as done.");
        assertEquals(LocalDateTime.of(2023, 9, 20, 23, 59), ((Deadline) tasks
                        .get(1)).getDateTime(),
                "Deadline date mismatch for the second task.");

        assertTrue(tasks.get(2) instanceof Event, "Third task should be an Event.");
        assertEquals(" ", tasks.get(2).getStatusIcon(), "Third task should not be marked as done.");
        assertEquals(LocalDateTime.of(2023, 9, 20, 18, 0), ((Event) tasks
                        .get(2)).getStartDateTime(),
                "Event start date mismatch.");
        assertEquals(LocalDateTime.of(2023, 9, 20, 21, 0), ((Event) tasks
                        .get(2)).getEndDateTime(),
                "Event end date mismatch.");

        assertTrue(tasks.get(3) instanceof FixedDuration, "Fourth task should be FixedDuration.");
        assertEquals("X", tasks.get(3).getStatusIcon(), "Fourth task should be marked as done.");
        assertEquals(2, ((FixedDuration) tasks.get(3)).getDuration(), "Fixed duration mismatch.");
    }

    @Test
    void load_fileWithUnknownTaskType_throwsUnknownTaskTypeException() throws Exception {
        writeToFile("X | 0 | Invalid task");
        assertThrows(UnknownTaskTypeException.class, () -> storage.load(),
                "Expected UnknownTaskTypeException to be thrown for invalid task type.");
    }

    @Test
    void load_nonExistentFile_throwsBingBongException() {
        File file = new File(TEST_FILE_PATH);
        file.delete();

        BingBongException thrown = assertThrows(BingBongException.class, () -> storage.load(),
                "Expected BingBongException to be thrown for a missing file.");
        assertTrue(thrown.getMessage().contains("Error reading from file"), "Error message mismatch.");
    }

    @Test
    void save_validTaskList_savesSuccessfully() throws BingBongException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Read a book"));
        tasks.add(new Deadline("Submit assignment", LocalDateTime.of(2023, 9, 20,
                23, 59)));
        tasks.add(new Event("Birthday party", LocalDateTime.of(2023, 9, 20, 18,
                0), LocalDateTime.of(2023, 9, 20, 21, 0)));
        tasks.add(new FixedDuration("Exercise", 2));

        storage.save(tasks);

        List<Task> loadedTasks = storage.load();
        assertEquals(4, loadedTasks.size(), "Task list size after loading should be 4.");
        assertTrue(loadedTasks.get(0) instanceof ToDo, "First task after saving should be ToDo.");
    }

    @Test
    void save_nullTaskList_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> storage.save(null),
                "Expected AssertionError to be thrown when saving null TaskList.");
    }

    @Test
    void ensureDirectoryExists_createsDirectorySuccessfully() throws BingBongException {
        Storage dirStorage = new Storage("temp/test_storage.txt");
        File directory = new File("temp");
        directory.delete();

        dirStorage.save(new TaskList());
        assertTrue(directory.exists() && directory.isDirectory(), "Directory should be created successfully.");

        directory.delete();
    }

    // Helper method to write directly to the test file
    private void writeToFile(String content) throws Exception {
        FileWriter writer = new FileWriter(TEST_FILE_PATH);
        writer.write(content);
        writer.close();
    }
}
