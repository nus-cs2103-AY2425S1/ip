package pixy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pixy.storage.Storage;
import pixy.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private Storage storage;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file to simulate task storage
        tempFile = File.createTempFile("tasks", ".txt");
        // Write sample tasks to the file
        List<String> tasks = List.of(
                "T |   | read book",
                "D | X | submit assignment | Sep 10 2024, 11:59 pm",
                "E |   | team meeting | Aug 31 2024, 9:00 am | Aug 31 2024, 10:00 am"
        );
        Files.write(tempFile.toPath(), tasks);

        // Initialize the Storage class with the temporary file path
        storage = new Storage(tempFile.getAbsolutePath());
    }

    @Test
    void testLoad() throws IOException {
        // Act: Load tasks from the file
        List<Task> tasks = storage.load();

        // Assert: Verify that the list contains the correct number of tasks
        assertEquals(3, tasks.size(), "The list should contain 3 tasks");

        // Verify that the tasks are correctly parsed
        assertEquals("read book", tasks.get(0).getDescription());
        assertEquals("submit assignment", tasks.get(1).getDescription());
        assertEquals("team meeting", tasks.get(2).getDescription());
    }

    @Test
    void testLoadFromEmptyFile() throws IOException {
        // Clear the file to simulate an empty task file
        Files.write(tempFile.toPath(), List.of());

        // Act: Load tasks from the empty file
        List<Task> tasks = storage.load();

        // Assert: Verify that the list is empty
        assertTrue(tasks.isEmpty(), "The tasks list should be empty when the file is empty");
    }
}
