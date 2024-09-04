package streams.util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.exception.StreamsException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StorageTest {

    private Storage storage;
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        storage = new Storage(tempDir.resolve("tasks.txt").toString());
    }

    @Test
    public void testSaveAndLoad() throws StreamsException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDoTask("Read book"));
        tasks.add(new DeadlineTask("Submit report", LocalDateTime.of(2023, 12, 31, 23, 59)));

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(tasks.size(), loadedTasks.size());
        assertEquals(tasks.get(0).toString(), loadedTasks.get(0).toString());
        assertEquals(tasks.get(1).toString(), loadedTasks.get(1).toString());
    }

    @Test
    public void testLoadNonExistentFile() throws StreamsException {
        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
    }
}
