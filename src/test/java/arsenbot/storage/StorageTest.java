package arsenbot.storage;

import arsenbot.task.Deadline;
import arsenbot.task.Event;
import arsenbot.task.Task;
import arsenbot.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    Path tempDir;

    private Storage storage;
    private Path filePath;

    @BeforeEach
    public void setUp() {
        filePath = tempDir.resolve("tasks.txt");
        storage = new Storage(filePath.toString());
    }

    @Test
    public void saveTasksToFile_validTasks_success() throws IOException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read book"));
        tasks.add(new Deadline("Submit report", "2024-09-10 1800"));
        tasks.add(new Event("Project meeting", "2024-09-12 1400", "2024-09-12 1600"));

        storage.save(tasks);

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(3, lines.size());
        assertEquals("T | 0 | Read book", lines.get(0));
        assertEquals("D | 0 | Submit report | 2024-09-10 1800", lines.get(1));
        assertEquals("E | 0 | Project meeting | 2024-09-12 1400 | 2024-09-12 1600", lines.get(2));
    }

    @Test
    public void loadTasksFromFile_validFile_success() throws IOException {
        List<String> lines = List.of(
                "T | 0 | Read book",
                "D | 1 | Submit report | 2024-09-10 1800",
                "E | 0 | Project meeting | 2024-09-12 1400 | 2024-09-12 1600"
        );
        Files.write(filePath, lines);

        List<Task> tasks = storage.load();

        assertEquals(3, tasks.size());

        assertTrue(tasks.get(0) instanceof Todo);
        assertEquals("[T][ ] Read book", tasks.get(0).toString());

        assertTrue(tasks.get(1) instanceof Deadline);
        assertEquals("[D][X] Submit report (by: Sep 10 2024, 6:00 PM)", ((Deadline) tasks.get(1)).toString());

        assertTrue(tasks.get(2) instanceof Event);
        assertEquals("[E][ ] Project meeting (from: Sep 12 2024, 2:00 PM to: Sep 12 2024, 4:00 PM)", ((Event) tasks.get(2)).toString());
    }
}
