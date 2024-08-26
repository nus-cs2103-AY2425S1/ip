package friday.util;

import friday.task.Task;
import friday.task.TaskList;
import friday.task.Todo;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void loadTasks_returnsCorrectTasks() throws IOException {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        storage.saveTasks(taskList.getTasks());
        ArrayList<Task> loadedTaskList = storage.loadTasks();
        assertEquals(taskList.getSize(), loadedTaskList.size());
    }

    @Test
    void saveTasks_savesCorrectly() throws IOException {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        storage.saveTasks(taskList.getTasks());
        ArrayList<Task> loadedTaskList = storage.loadTasks();
        assertEquals("T | 0 | read book", loadedTaskList.get(0).toFileFormat());
    }
}
