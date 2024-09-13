package storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDo;
import task.Event;
import task.Deadline;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private Storage storage;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private final String testFilePath = "test_tasks.txt";

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testFileCreation() {
        File file = new File(testFilePath);
        assertTrue(file.exists(), "File should be created");
    }

    @Test
    public void testSaveTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        taskList.add(new ToDo("Read book"));
        taskList.add(new Event("Meeting", now, now.plusHours(1)));
        taskList.add(new Deadline("Submit report", now.plusDays(1)));
        storage.saveTasks(taskList);
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(taskList.size(), loadedTasks.size(), "Task list size should match");
        assertTrue(loadedTasks.get(0) instanceof ToDo, "First task should be a ToDo");
        assertEquals("Read book", loadedTasks.get(0).getDescription(), "Description should match");
        assertTrue(loadedTasks.get(1) instanceof Event, "Second task should be an Event");
        assertEquals("Meeting", loadedTasks.get(1).getDescription(), "Description should match");
        assertEquals(now.format(FORMATTER), ((Event) loadedTasks.get(1)).getStartDate().format(FORMATTER), "Start date should match");
        assertEquals(now.plusHours(1).format(FORMATTER), ((Event) loadedTasks.get(1)).getEndDate().format(FORMATTER), "End date should match");
        assertTrue(loadedTasks.get(2) instanceof Deadline, "Third task should be a Deadline");
        assertEquals("Submit report", loadedTasks.get(2).getDescription(), "Description should match");
        assertEquals(now.plusDays(1).format(FORMATTER), ((Deadline) loadedTasks.get(2)).getBy().format(FORMATTER), "By date should match");
    }
}
