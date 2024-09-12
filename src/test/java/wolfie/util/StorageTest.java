// src/test/java/wolfie/util/StorageTest.java
package wolfie.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.task.Deadline;
import wolfie.task.Event;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.task.Todo;

class StorageTest {
    private Storage storage;
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        String testFilePath = "data/testTasks.txt";
        storage = new Storage(testFilePath);
        tasks = new TaskList();
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

    @Test
    void testLoadAndSave() throws IOException {
        tasks.add(new Todo("Test Todo", false));
        tasks.add(new Deadline("Test Deadline", LocalDateTime.of(2023, 10, 1, 12, 0), false));
        tasks.add(new Event("Test Event", LocalDateTime.of(2023, 10, 1, 10, 0), LocalDateTime.of(2023, 10, 1, 12, 0), false));

        storage.save(tasks);
        List<Task> loadedTasks = storage.load();

        assertEquals(3, loadedTasks.size());
        assertEquals("Test Todo", loadedTasks.get(0).getDescription());
        assertEquals("Test Deadline", loadedTasks.get(1).getDescription());
        assertEquals("Test Event", loadedTasks.get(2).getDescription());
    }
}
