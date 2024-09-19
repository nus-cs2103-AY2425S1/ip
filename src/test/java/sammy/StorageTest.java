package sammy;

import org.junit.jupiter.api.Test;
import sammy.task.Task;
import sammy.task.TaskList;
import sammy.task.Todo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void testLoadTasks() throws IOException, SammyException {
        Storage storage = new Storage("path_to_your_test_file");
        List<Task> loadedTasks = storage.load();
        TaskList tasks = new TaskList(loadedTasks);
    }

    @Test
    void testSaveTasks() throws IOException {
        Storage storage = new Storage("data/testTasks.txt");
        TaskList tasks = new TaskList();
        tasks.add(new Todo("New Task"));
        storage.save(tasks);
        File file = new File("data/testTasks.txt");
        assertTrue(file.exists());
    }
}
