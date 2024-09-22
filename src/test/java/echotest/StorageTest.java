package echotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import echo.EchoException;
import echo.Storage;
import echo.TaskList;
import echo.task.Task;

class StorageTest {
    private Storage storage;
    private final String testFilePath = "docs/tasks.txt";

    @BeforeEach
    void setUp() {
        storage = new Storage(testFilePath);
    }

    @Test
    void testLoad() throws EchoException, IOException {
        List<Task> tasks = storage.load();
        assertNotNull(tasks);
    }

    @Test
    void testSave() throws IOException {
        TaskList taskList = new TaskList();
        taskList.addTodo("Sample task");
        storage.save(taskList.getTasks());

        File file = new File(testFilePath);
        assertTrue(file.exists());
    }

    @Test
    void testLoadAfterSave() throws EchoException, IOException {
        TaskList taskList = new TaskList();
        taskList.addTodo("Sample task");
        String init = taskList.listToString();
        storage.save(taskList.getTasks());

        List<Task> tasks = storage.load();
        TaskList taskListTest = new TaskList(tasks);
        assertEquals(TaskList.listToString(tasks), init);

    }
}

