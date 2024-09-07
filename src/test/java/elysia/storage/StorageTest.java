package elysia.storage;

import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.task.ToDos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private Storage storage;
    private File tempFile;
    private ArrayList<Task> tasks;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
        storage = new Storage(tasks);
    }

    @AfterEach
    public void cleanUp() {
        if ((tempFile!=null) && tempFile.exists()) {
            try {
                Files.delete(tempFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Failed to clean up the tempFile", e);
            }
        }
    }

    @Test
    public void storage_testAddToDos() throws FileNotFoundException {
        storage.scanFileContents(createTestFile("T | 0 | borrow book\n"));

        assertEquals(1, tasks.size());
        assertInstanceOf(ToDos.class, tasks.get(0));
        assertEquals("borrow book", tasks.get(0).getDescription());
        assertEquals("[ ]", tasks.get(0).getStatusIcon());
    }

    @Test
    public void storage_testAddDeadline() throws FileNotFoundException {
        storage.scanFileContents(createTestFile("D | 1 | return book | 2024-09-02\n"));

        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        assertEquals("return book", tasks.get(0).getDescription());
        assertEquals("[X]", tasks.get(0).getStatusIcon());
    }

    @Test
    public void testHandleExit() throws IOException {
        tasks.add(new ToDos("read book"));
        tasks.add(new Deadline("return book", LocalDate.of(2024,9,2)));
        tasks.add(new Event("project meeting", "Mon 6pm", "8pm"));

        //append the given string to tempDir
        Path filePath = tempDir.resolve("tasks.txt");

        storage.handleExit(tempDir.toString(), "tasks.txt", filePath.toString());

        //check the file's content
        String content = Files.readString(filePath);
        System.out.println(content);
        assertTrue(content.contains("T | 0 | read book"));
        assertTrue(content.contains("D | 0 | return book | 2024-09-02"));
        assertTrue(content.contains("E | 0 | project meeting | Mon 6pm | 8pm"));
    }

    private String createTestFile(String s) {
        try {
            tempFile = Files.createTempFile(tempDir, "test", ".txt").toFile();
            FileWriter writer = new FileWriter(tempFile);
            writer.write(s);
            writer.close();

            tempFile = Files.createTempFile(tempDir, "test", ".txt").toFile();
            Files.write(tempFile.toPath(), s.getBytes());
            return tempFile.getAbsolutePath();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create test file", e);
        }
    }
}
