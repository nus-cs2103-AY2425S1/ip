package myapp.storage;

import myapp.exception.RubyException;
import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.Task;
import myapp.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private Storage storage;
    private final String tempFilePath = "test_tasks.txt";

    @BeforeEach
    public void setUp() {
        storage = new Storage(tempFilePath);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(tempFilePath));
    }

    @Test
    public void testLoad_emptyFile() throws IOException, RubyException {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Task list should be empty when loading from an empty file");
    }

    @Test
    public void testLoad_validTasks() throws IOException, RubyException {
        String content = """
                T | 0 | Read book
                D | 1 | Submit assignment | 2024-09-01 1800
                E | 0 | Project meeting | 2024-09-01 1400 | 2024-09-01 1600
                """;
        Files.write(Path.of(tempFilePath), content.getBytes());

        List<Task> tasks = storage.load();
        assertEquals(3, tasks.size(), "There should be three tasks loaded from the file");

        assertInstanceOf(Todo.class, tasks.get(0), "First task should be a Todo");
        assertFalse(tasks.get(0).isDone(), "First task should not be done");
        assertEquals("Read book", tasks.get(0).getDescription());

        assertInstanceOf(Deadline.class, tasks.get(1), "Second task should be a Deadline");
        assertTrue(tasks.get(1).isDone(), "Second task should be done");
        assertEquals("Submit assignment", tasks.get(1).getDescription());

        assertInstanceOf(Event.class, tasks.get(2), "Third task should be an Event");
        assertFalse(tasks.get(2).isDone(), "Third task should not be done");
        assertEquals("Project meeting", tasks.get(2).getDescription());
    }

    @Test
    public void testLoad_invalidTaskType() throws IOException {
        String content = "X | 0 | Invalid task\n";
        Files.write(Path.of(tempFilePath), content.getBytes());

        assertThrows(RubyException.class, () -> {
            storage.load();
        }, "Loading an invalid task type should throw RubyException");
    }

    @Test
    public void testSave_tasksToFile() throws IOException, RubyException {
        Todo todo = new Todo("Read book");
        Deadline deadline = new Deadline("Submit assignment", LocalDateTime.of(2024, 9, 1, 18, 0));
        Event event = new Event("Project meeting", LocalDateTime.of(2024, 9, 1, 14, 0), LocalDateTime.of(2024, 9, 1, 16, 0));

        todo.markAsDone();
        List<Task> tasks = List.of(todo, deadline, event);
        storage.save(tasks);

        List<String> lines = Files.readAllLines(Path.of(tempFilePath));
        assertEquals(3, lines.size(), "There should be three lines in the file");

        assertEquals("T | 1 | Read book", lines.get(0), "First line should represent the saved Todo task");
        assertEquals("D | 0 | Submit assignment | 2024-09-01 1800", lines.get(1), "Second line should represent the saved Deadline task");
        assertEquals("E | 0 | Project meeting | 2024-09-01 1400 | 2024-09-01 1600", lines.get(2), "Third line should represent the saved Event task");
    }
}
