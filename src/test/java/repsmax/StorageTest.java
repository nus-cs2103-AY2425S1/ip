package repsmax;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testSave_andLoad_tasksArePersisted() throws Exception {
        // Arrange
        String filePath = "testTasks.txt";
        Storage storage = new Storage(filePath);
        TaskList tasks = new TaskList();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Task 2"));

        // Act
        storage.save(tasks);
        TaskList loadedTasks = new TaskList();
        storage.load(loadedTasks);

        // Assert
        assertEquals(2, loadedTasks.size());
        assertEquals("[ ] Task 1", loadedTasks.get(0).toString());
        assertEquals("[ ] Task 2", loadedTasks.get(1).toString());

        // Cleanup
        new File(filePath).delete();
    }

    @Test
    public void testLoad_noFile_createsNewList() {
        // Arrange
        String filePath = "nonExistentFile.txt";
        Storage storage = new Storage(filePath);
        TaskList tasks = new TaskList();

        // Act
        storage.load(tasks);

        // Assert
        assertEquals(0, tasks.size());
    }
}
