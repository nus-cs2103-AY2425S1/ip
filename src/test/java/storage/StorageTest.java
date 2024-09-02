package storage;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import exception.DudeException;

public class StorageTest {

    @Test
    public void testSaveAndLoad() throws DudeException, IOException {
        Storage storage = new Storage("test.txt");
        TaskList tasks = new TaskList();
        tasks.addTask(new task.Todo("read book"));
        storage.saveTasks(tasks);
        assert(storage.initTasks().getSize() == 1
                    && storage.initTasks().getTask(0).toString().equals("[T][ ] read book"));
    }

    @Test
    public void testStorageEmpty() throws IOException, DudeException {
        Storage storage = new Storage("test.txt");
        TaskList tasks = new TaskList();
        storage.saveTasks(tasks);
        assert(storage.initTasks().getSize() == 0);
    }
}
