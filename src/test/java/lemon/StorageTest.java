package lemon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lemon.task.Task;

public class StorageTest {

    @Test
    public void testLoadTasks() {
        Storage s = new Storage("data/test/lemonLoadTest.txt");
        TaskList tl = new TaskList();
        assertTrue(s.loadTasks(tl));
    }

    @Test
    public void testSaveTasks() {
        Storage s = new Storage("data/test/lemonSavesTest.txt");
        Task test1 = new TaskStub();
        Task test2 = new TaskStub();
        TaskList tl = new TaskList();

        tl.addNewTask(test1);
        tl.addNewTask(test2);
        assertTrue(s.saveTasks(tl));
    }

}
