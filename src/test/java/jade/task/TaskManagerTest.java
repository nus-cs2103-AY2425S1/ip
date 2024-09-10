package jade.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jade.exception.JadeException;
import jade.storage.Storage;

public class TaskManagerTest {
    private Storage storage;
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        storage = new Storage("data/test_tasks.txt");
        taskManager = new TaskManager(storage);
    }

    @Test
    void testAddTask() {
        Task task = new Todo("Read a book");
        taskManager.addTask(task);

        assertEquals(1, taskManager.getTaskCount());
        assertEquals("Read a book", taskManager.getTask(0).getDescription());
    }

    @Test
    void testMarkTask() {
        taskManager.markTask(0, true);
        assertEquals("X", taskManager.getTask(0).getStatusIcon());

        taskManager.markTask(0, false);
        assertEquals(" ", taskManager.getTask(0).getStatusIcon());
    }

    @Test
    void testDeleteTask() throws JadeException {
        taskManager.deleteTask(0);
        assertEquals(0, taskManager.getTaskCount());
    }
}
