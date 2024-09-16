package utilities;

import java.util.ArrayList;

import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests Storage class for non-trivial functions.
 * Assumes data files starts with values from input.txt
 * and initial length is of length 4.
 */
public class StorageTest {
    private static final String FILE_PATH = "./tasks/data.txt"; 

    @Test
    public void loadTasksFromFileTest() {
        Storage store = new Storage(FILE_PATH);
        ArrayList<Task> tl = store.loadTaskListFromFile();
        // Check for length of tasks read
        assertEquals(tl.size(), 4);

        // Check for correct datetime of event read
        Task t = tl.get(2);
        assertEquals("2024-12-29 12:00, 2024-12-29 17:00", t.getWriteTaskInfo());
    }

    @Test
    public void updateTaskStatusInFileTest() {
        Storage store = new Storage(FILE_PATH);
        store.updateTaskStatus(3, true);
        // Check for the correct update of task status
        ArrayList<Task> tl = store.loadTaskListFromFile();
        Task t = tl.get(3);
        assertEquals("X", t.getTaskStatus());

        store.updateTaskStatus(2, false);
        ArrayList<Task> tl2 = store.loadTaskListFromFile();
        Task t2 = tl2.get(2);
        assertEquals(" ", t2.getTaskStatus());
    }

    @Test
    public void updateTaskTagInFileTest() {
        Storage store = new Storage(FILE_PATH);
        store.updateTaskTag(3, "fun");
        // Check for the addition of tag
        ArrayList<Task> tl = store.loadTaskListFromFile();
        String tag = tl.get(3).getTag();
        assertEquals("#fun", tag);

        store.updateTaskTag(3, "important");
        // Check for overwriting of the tag
        ArrayList<Task> tl2 = store.loadTaskListFromFile();
        String tag2 = tl2.get(3).getTag();
        assertEquals("#important", tag2);
    }
}
