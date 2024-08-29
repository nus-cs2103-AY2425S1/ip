package util;

import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

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
