package lolo.storage;

import lolo.LoloException;
import lolo.task.Deadline;
import lolo.task.Event;
import lolo.task.Task;
import lolo.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final String TEST_FILE_PATH = "testStorage.txt";

    @Test
    public void testSaveAndLoadTasks() throws LoloException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Task 1"));
        tasks.add(new Deadline("Deadline 1", LocalDateTime.of(2024, 8, 29, 10, 0)));
        tasks.add(new Event("Event 1", LocalDateTime.of(2024, 8, 30, 9, 0), LocalDateTime.of(2024, 8, 30, 17, 0)));

        Storage storage = new Storage(TEST_FILE_PATH);
        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(3, loadedTasks.size(), "There should be 3 tasks loaded.");
        assertTrue(loadedTasks.get(0) instanceof ToDo, "First task should be of type ToDo.");
        assertTrue(loadedTasks.get(1) instanceof Deadline, "Second task should be of type Deadline.");
        assertTrue(loadedTasks.get(2) instanceof Event, "Third task should be of type Event.");
    }

}
