package util;

import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the Storage class.
 */
public class StorageTest {

    /**
     * Tests saving and loading tasks using the Storage class.
     *
     * @throws IOException If an I/O error occurs during saving or loading.
     */
    @Test
    public void testSaveAndLoad() throws IOException {
        Storage storage = new Storage("data/test_tasks.txt");
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Test task");
        taskList.add(todo);

        storage.save(taskList);
        TaskList loadedList = new TaskList(storage.load());
        assertEquals(1, loadedList.getTasks().size());
        assertEquals("Test task", loadedList.getTasks().get(0).getName());
    }
}
