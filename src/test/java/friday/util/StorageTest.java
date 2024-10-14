package friday.util;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import friday.task.Task;
import friday.task.TaskList;
import friday.task.Todo;

class StorageTest {

    @Test
    void loadTasks_returnsCorrectTasks() throws IOException {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        storage.saveTasks(taskList.getTasks());
        ArrayList<Task> loadedTaskList = storage.loadTasks();
        Assertions.assertEquals(taskList.getSize(), loadedTaskList.size());
    }

    @Test
    void saveTasks_savesCorrectly() throws IOException {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        storage.saveTasks(taskList.getTasks());
        ArrayList<Task> loadedTaskList = storage.loadTasks();
        Assertions.assertEquals("T | 0 | read book", loadedTaskList.get(0).toFileFormat());
    }
}
