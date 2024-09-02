package components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Todo;

public class StorageTest {
    private final String testFilePath = "./data/test_storage.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
    }

    @Test
    public void testLoadEmptyFile() throws IOException {
        // Create an empty file
        new FileWriter(testFilePath).close();

        // Load tasks from the empty file
        ArrayList<Task> tasks = storage.load();

        // Verify the tasks list is empty
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testLoadWithTasks() throws IOException {
        // Create a file with some tasks
        FileWriter writer = new FileWriter(testFilePath);
        writer.write("1. [T][ ] test\n");
        writer.close();

        // Load tasks from the file
        ArrayList<Task> tasks = storage.load();

        // Verify the tasks list contains the expected task
        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
        assertEquals("[T][ ] test", tasks.get(0).toString());
    }

    @Test
    public void testWrite() throws IOException {
        // Write some tasks to the file
        String content = "1. [T][ ] test\n";
        storage.write(content);

        // Read the file content
        File file = new File(testFilePath);
        Scanner scanner = new Scanner(file);
        StringBuilder fileContent = new StringBuilder();
        while (scanner.hasNextLine()) {
            fileContent.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        // Verify the file content
        assertEquals(content, fileContent.toString());
    }
}