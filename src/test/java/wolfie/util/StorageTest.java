// src/test/java/wolfie/util/StorageTest.java
package wolfie.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.task.Todo;

class StorageTest {
    private Storage storage;

    @BeforeEach
    void setUp() {
        String testFilePath = "data/tasks.txt";
        storage = new Storage(testFilePath);
    }

    @Test
    void testLoad() throws IOException {
        // Prepare test data
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test task", false));
        storage.save(taskList);

        // Load tasks from file
        List<Task> tasks = storage.load();
        assertEquals(1, tasks.size());
        assertEquals("Test task", tasks.get(0).getDescription());
    }

    @Test
    void testSave() throws IOException {
        // Prepare test data
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test task", false));

        // Save tasks to file
        storage.save(taskList);

        // Verify file content
        List<Task> tasks = storage.load();
        assertEquals(1, tasks.size());
        assertEquals("Test task", tasks.get(0).getDescription());
    }
}
