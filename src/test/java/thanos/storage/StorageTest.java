package thanos.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.tasks.Deadline;
import thanos.tasks.Event;
import thanos.tasks.Task;
import thanos.tasks.Todo;

public class StorageTest {
    private Path filePath;
    private IStorage storage;

    @BeforeEach
    public void setUp() throws IOException {
        String fileName = "test.txt";
        filePath = Paths.get("data", fileName);
        Files.createDirectories(filePath.getParent());
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        storage = new Storage(fileName);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(filePath);
    }

    @Test
    public void testLoadFromEmptyFile() {
        ArrayList<Task> tasks = storage.load();
        assertEquals(0, tasks.size());
    }

    @Test
    public void testLoadTasksFromFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("T | 1 | read book");
            writer.newLine();
            writer.write("D | 0 | submit report | Aug 26 2024 00:00");
            writer.newLine();
            writer.write("E | 0 | Dentist appointment | Aug 31 2024 14:00-Aug 31 2024 16:00");
            writer.newLine();
        }

        ArrayList<Task> tasks = storage.load();

        assertEquals(3, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
        assertInstanceOf(Event.class, tasks.get(2));
    }

    @Test
    public void testSaveTasksToFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read a book"));
        tasks.add(new Deadline("Submit assignment", LocalDateTime.of(2024, 8, 31, 23, 59)));

        storage.save(tasks);

        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));
        assertEquals(2, lines.size());
        assertEquals("T | 0 | Read a book", lines.get(0));
        assertEquals("D | 0 | Submit assignment | Aug 31 2024 23:59", lines.get(1));
    }

    @Test
    public void testSaveEmptyTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        storage.save(tasks);
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));

        assertEquals(0, lines.size());
    }
}
