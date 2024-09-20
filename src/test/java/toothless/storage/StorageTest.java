package toothless.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import toothless.exceptions.ToothlessExceptions;
import toothless.task.Deadline;
import toothless.task.Event;
import toothless.task.Task;
import toothless.task.ToDo;

/**
 * Tests for Storage class.
 */
public class StorageTest {

    private static final String TEST_DATA_FILE_PATH = "src/data/test_tasks.txt";
    private Storage storage;
    private ArrayList<Task> tasks;

    @BeforeEach
    public void setUp() throws ToothlessExceptions {
        storage = new Storage();
        tasks = new ArrayList<>();
        tasks.add(new ToDo("Test ToDo"));
        tasks.add(new Deadline("Test Deadline", "12/12/2050 1800"));
        tasks.add(new Event("Test Event", "12/12/2050 1000", "12/12/2050 1200"));
    }

    @Test
    public void testParseDataToTask() {
        String todoData = "T | 0 | Test ToDo";
        String deadlineData = "D | 0 | Test Deadline | 12/12/2050 1800";
        String eventData = "E | 0 | Test Event | 12/12/2050 1000 | 12/12/2050 1200";

        Task todo = storage.parseDataToTask(todoData);
        Task deadline = storage.parseDataToTask(deadlineData);
        Task event = storage.parseDataToTask(eventData);

        assertEquals("[T][ ] Test ToDo", todo.toString());
        assertEquals("[D][ ] Test Deadline (by: 12 Dec 2050 18:00)", deadline.toString());
        assertEquals("[E][ ] Test Event (from: 12 Dec 2050 10:00 to: 12 Dec 2050 12:00)", event.toString());
    }
}
