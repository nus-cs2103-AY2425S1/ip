package seedu.kafka;

import kafka.Deadline;
import kafka.Storage;
import kafka.Todo;
import kafka.Event;
import kafka.Task;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {

    @Test
    public void testWriteTodoToFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Sample todo", false));

        Path tempFile = Files.createTempFile("test", ".txt");
        Storage storage = new Storage(tempFile.toString());
        storage.writeToFile(tasks);

        String expected = "T | false | Sample todo" + System.lineSeparator();
        String content = Files.readString(tempFile);

        assertEquals(expected, content);
    }

    @Test
    public void testWriteDeadlineToFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("Sample deadline", LocalDateTime.of(2024, 12, 31, 23, 59), false));

        Path tempFile = Files.createTempFile("test", ".txt");
        Storage storage = new Storage(tempFile.toString());
        storage.writeToFile(tasks);

        String expected = "D | false | Sample deadline | 2024-12-31T23:59" + System.lineSeparator();
        String content = Files.readString(tempFile);

        assertEquals(expected, content);
    }

    @Test
    public void testWriteEventToFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("Sample event",
                LocalDateTime.of(2024, 12, 1, 12, 0),
                LocalDateTime.of(2024, 12, 1, 14, 0), false));

        Path tempFile = Files.createTempFile("test", ".txt");
        Storage storage = new Storage(tempFile.toString());
        storage.writeToFile(tasks);

        String expected = "E | false | Sample event | 2024-12-01T12:00 | 2024-12-01T14:00" + System.lineSeparator();
        String content = Files.readString(tempFile);

        assertEquals(expected, content);
    }
}
