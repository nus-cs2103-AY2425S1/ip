package cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import cheese.exception.CheeseException;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.ToDo;

/**
 * Class to test Storage class in cheese package
 */
public class StorageTest {
    @TempDir
    File tempDir;

    private Deadline deadline = new Deadline("D,1,abc,2025-04-03".split(","));
    private Event event = new Event("E,0,abc ,2024-03-04,2025-04-03".split(","));
    private ToDo toDo = new ToDo("Read a book");

    public StorageTest() throws CheeseException {
    }

    @Test
    public void testLoadTasksValidData() throws CheeseException, IOException {
        File tempFile = new File(tempDir, "testTasks.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("D,1,abc,2025-04-03\n");
            writer.write("E,0,abc,2024-03-04,2025-04-03\n");
            writer.write("T,0,babt\n");
        }
        Storage storage = new Storage(tempFile.getAbsolutePath());

        ArrayList<Task> tasks = storage.loadTasks();
        assertEquals(3, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        assertInstanceOf(Event.class, tasks.get(1));
        assertInstanceOf(ToDo.class, tasks.get(2));
    }

    @Test
    public void testLoadTasksInvalidDataFormat() throws IOException {
        File tempFile = new File(tempDir, "testTasks.txt");

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("X,0,Unknown task format\n"); // Invalid task type
        }

        Storage storage = new Storage(tempFile.getAbsolutePath());

        CheeseException exception = assertThrows(CheeseException.class, storage::loadTasks);
        assertEquals("Load tasks failed.Incorrect data format", exception.getMessage());
    }

    @Test
    public void testAddTask() throws CheeseException, IOException {
        File tempFile = new File(tempDir, "testTasks.txt");
        Storage storage = new Storage(tempFile.getAbsolutePath());

        Task task = new ToDo("Read a book");
        storage.addTask(task);

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line = reader.readLine();
            assertEquals("T,0,Read a book", line);
        }
    }

    @Test
    public void testUpdateTask() throws CheeseException, IOException {
        File tempFile = new File(tempDir, "testTasks.txt");

        Storage storage = new Storage(tempFile.getAbsolutePath());
        storage.addTask(toDo);
        storage.addTask(deadline);

        TaskList tasks = new TaskList();
        tasks.add(toDo);
        tasks.add(deadline);
        storage.updateTask(1, tasks, true); //delete deadline

        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        assertEquals(1, lines.size());
        assertEquals("T,0,Read a book", lines.get(0));
    }

    @Test
    public void testUpdateTaskNoDelete() throws CheeseException, IOException {
        File tempFile = new File(tempDir, "testTasks.txt");

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("T,0,Read a book\n");
            writer.write("D,1,abc,2025-04-03\n");
            writer.write("E,0,abc ,2024-03-04,2025-04-03\n");
        }

        Storage storage = new Storage(tempFile.getAbsolutePath());

        TaskList tasks = new TaskList();
        tasks.add(toDo); // Unchanged
        tasks.add(new Deadline("D,1,Submit updated report,2024-08-23".split(","))); // Updated
        tasks.add(event); // Unchanged

        storage.updateTask(1, tasks, false);

        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        assertEquals(3, lines.size());
        assertEquals("T,0,Read a book", lines.get(0));
        assertEquals("D,1,Submit updated report,2024-08-23", lines.get(1));
        assertEquals("E,0,abc ,2024-03-04,2025-04-03", lines.get(2));
    }
}
