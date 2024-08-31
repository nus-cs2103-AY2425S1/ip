package seedu.maxine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    public void testLoadStorage_givesError() {
        // Using a file path that does not exist
        Storage storage = new Storage("invalid/path/to/file.txt");
        storage.load();
        String expectedOutput = "Oh no! I can't seem to find the file :(";
        String actualOutput = outputStream.toString().trim();
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testTodoLoadStorage() {
        Storage storage = new Storage("data/todotest.txt");
        ArrayList<Task> list = storage.load();
        Task task = list.get(0);
        assertInstanceOf(Todo.class, task);
    }
    @Test
    public void testDeadlineLoadStorage() {
        Storage storage = new Storage("data/deadlinetest.txt");
        ArrayList<Task> list = storage.load();
        Task task = list.get(0);
        assertInstanceOf(Deadline.class, task);
    }
    @Test
    public void testEventLoadStorage() {
        Storage storage = new Storage("data/eventtest.txt");
        ArrayList<Task> list = storage.load();
        Task task = list.get(0);
        assertInstanceOf(Event.class, task);
    }
}

