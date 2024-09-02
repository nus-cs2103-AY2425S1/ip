package justbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import justbot.exception.JustbotException;
import justbot.task.Deadline;
import justbot.task.Event;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.task.Todo;

public class StorageTest {

    @TempDir
    Path tempDir;
    private Storage storage;
    private String filePath;

    @BeforeEach
    public void setUp() {
        filePath = tempDir.resolve("tasks.txt").toString();
        storage = new Storage(filePath);
    }

    @Test
    public void testCreateFileIfDoesNotExist() {
        File file = new File(filePath);
        assertFalse(file.exists());

        storage.createFileIfDoesNotExist();

        assertTrue(file.exists());
    }

    @Test
    public void testSaveTasksAndLoadTasks() throws JustbotException, IOException {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new Todo("sleep"));
        taskList.add(new Deadline("gym", LocalDateTime.of(2024, 12, 31, 23, 59)));
        taskList.add(new Event("run", LocalDateTime.of(2024, 9, 1, 9, 0), LocalDateTime.of(2024, 9, 1, 17, 0)));

        storage.saveTasks(taskList);

        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertEquals(3, lines.size());
        assertTrue(lines.get(0).contains("Todo"));
        assertTrue(lines.get(1).contains("Deadline"));
        assertTrue(lines.get(2).contains("Event"));

        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(3, loadedTasks.size());

        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertEquals("sleep", loadedTasks.get(0).getTaskDescription());

        assertTrue(loadedTasks.get(1) instanceof Deadline);
        assertEquals("gym", loadedTasks.get(1).getTaskDescription());

        assertTrue(loadedTasks.get(2) instanceof Event);
        assertEquals("run", loadedTasks.get(2).getTaskDescription());
    }

    @Test
    public void testLoadTasksWithMalformedLine() throws JustbotException, IOException {
        Files.write(Path.of(filePath), List.of(
                "Todo | x | Buy milk | No time constraint",
                "Deadline | x | Submit assignment | 31/12/2024 2359",
                "This is a malformed line that should be skipped",
                "Event | | Meeting | from 01/09/2024 0900 to 01/09/2024 1700"
        ));

        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(3, loadedTasks.size());
    }

    @Test
    public void testLoadTasksWithMalformedDate() throws JustbotException, IOException {
        Files.write(Path.of(filePath), List.of(
                "Todo | x | Buy milk | No time constraint",
                "Deadline | x | Submit assignment | 31/13/2024 2359",
                "Event | | Meeting | from 01/09/2024 0900 to 01/09/2024 1700"
        ));

        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(2, loadedTasks.size());
    }

    @Test
    public void testLoadTasksWithInvalidTaskType() throws JustbotException, IOException {
        Files.write(Path.of(filePath), List.of(
                "Todo | x | Buy milk | No time constraint",
                "InvalidTaskType | x | Some task | 31/12/2024 2359",
                "Event | | Meeting | from 01/09/2024 0900 to 01/09/2024 1700"
        ));

        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(2, loadedTasks.size());
    }
}
