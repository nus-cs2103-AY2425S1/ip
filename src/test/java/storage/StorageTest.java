package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

class StorageTest {

    private Path tempFile;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("test", ".txt");
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testRetrieveTasksEmptyFile() {
        List<Task> tasks = storage.retrieveTasks();
        assertTrue(tasks.isEmpty(), "Task list should be empty when no tasks are in the file");
    }

    @Test
    void testRetrieveTasksWithTasks() throws IOException {
        List<String> lines = List.of(
                "T | 0 | Read a book",
                "D | 1 | Submit report | 2024-08-30T18:00",
                "E | 0 | Team meeting | 2024-08-30T09:00 | 2024-08-30T11:00"
        );
        Files.write(tempFile, lines, StandardOpenOption.WRITE);

        List<Task> tasks = storage.retrieveTasks();

        assertEquals(3, tasks.size(), "Task list should have three tasks");
        assertTrue(tasks.get(0) instanceof Todo, "First task should be a Todo");
        assertEquals("Read a book", tasks.get(0).getName());

        assertTrue(tasks.get(1) instanceof Deadline, "Second task should be a Deadline");
        assertEquals("Submit report", tasks.get(1).getName());
        assertEquals(LocalDateTime.of(2024, 8, 30, 18, 0), ((Deadline) tasks.get(1)).getTime());

        assertTrue(tasks.get(2) instanceof Event, "Third task should be an Event");
        assertEquals("Team meeting", tasks.get(2).getName());
        assertEquals(LocalDateTime.of(2024, 8, 30, 9, 0), ((Event) tasks.get(2)).getStartTime());
        assertEquals(LocalDateTime.of(2024, 8, 30, 11, 0), ((Event) tasks.get(2)).getEndTime());
    }

    @Test
    void testMarkComplete() throws IOException {
        List<String> lines = List.of("T | 0 | Read a book");
        Files.write(tempFile, lines, StandardOpenOption.WRITE);

        List<Task> tasks = storage.retrieveTasks();
        Task task = tasks.get(0);
        storage.markComplete(task);

        List<String> updatedLines = Files.readAllLines(tempFile);
        assertTrue(updatedLines.get(0).contains("1"), "Task should be marked as complete in the file");
    }

    @Test
    void testMarkIncomplete() throws IOException {
        List<String> lines = List.of("T | 1 | Read a book");
        Files.write(tempFile, lines, StandardOpenOption.WRITE);

        List<Task> tasks = storage.retrieveTasks();
        Task task = tasks.get(0);
        storage.markIncomplete(task);

        List<String> updatedLines = Files.readAllLines(tempFile);
        assertTrue(updatedLines.get(0).contains("0"), "Task should be marked as incomplete in the file");
    }

    @Test
    void testDeleteTask() throws IOException {
        List<String> lines = List.of("T | 0 | Read a book", "D | 1 | Submit report | 2024-08-30T18:00");
        Files.write(tempFile, lines, StandardOpenOption.WRITE);

        List<Task> tasks = storage.retrieveTasks();
        Task task = tasks.get(0);
        storage.deleteTask(task);

        List<String> updatedLines = Files.readAllLines(tempFile);
        assertEquals(1, updatedLines.size(), "There should be one task left in the file");
        assertTrue(updatedLines.get(0).contains("Submit report"), "Remaining task should be 'Submit report'");
    }

    @Test
    void testWriteTodoToFile() throws IOException {
        Todo todo = new Todo("Read a book");
        storage.writeTodoToFile(todo);

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size(), "There should be one task in the file");
        assertTrue(lines.get(0).contains("T | 0 | Read a book"), "The task should be written as a Todo");
    }

    @Test
    void testWriteDeadlineToFile() throws IOException {
        Deadline deadline = new Deadline("Submit report", LocalDateTime.of(2024, 8, 30, 18, 0));
        storage.writeDeadlineToFile(deadline);

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size(), "There should be one task in the file");
        assertTrue(lines.get(0).contains("D | 0 | Submit report | 2024-08-30T18:00"),
                "The task should be written as a Deadline");
    }

    @Test
    void testWriteEventToFile() throws IOException {
        Event event = new Event("Team meeting", LocalDateTime.of(2024, 8, 30, 9, 0),
                LocalDateTime.of(2024, 8, 30, 11, 0));
        storage.writeEventToFile(event);

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size(), "There should be one task in the file");
        assertTrue(lines.get(0).contains("E | 0 | Team meeting | 2024-08-30T09:00 | 2024-08-30T11:00"),
                "The task should be written as an Event");
    }
}
