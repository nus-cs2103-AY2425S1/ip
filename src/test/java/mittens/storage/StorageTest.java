package mittens.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import mittens.task.TaskList;
import mittens.task.Todo;
import mittens.task.Deadline;
import mittens.task.Event;

public class StorageTest {
    @Test
    public void save_validTaskList_success(@TempDir Path tempDir) {
        Path filePath = tempDir.resolve("data.txt");
        
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("todoName"));
        tasks.addTask(new Deadline("deadlineName", LocalDate.of(2024, 1, 31)));
        tasks.addTask(new Event("eventName", LocalDate.of(2024, 1, 31), LocalDate.of(2024, 2, 1)));
        tasks.markTaskAsDone(1);
        
        Storage storage = new Storage(filePath.toString());
        try {
            storage.save(tasks);
        } catch (StorageFileException e) {
            fail();
        }
        
        try {
            List<String> lines = Files.readAllLines(filePath);
            assertEquals(3, lines.size());
            assertEquals("T| |todoName", lines.get(0));
            assertEquals("D|X|deadlineName|2024-01-31", lines.get(1));
            assertEquals("E| |eventName|2024-01-31|2024-02-01", lines.get(2));
        } catch (IOException e) {
            fail();
        }
    }
    
    @Test
    public void load_validTaskList_success(@TempDir Path tempDir) {
        Path filePath = tempDir.resolve("data.txt");
        try {
            Files.createFile(filePath);
            Files.write(filePath, List.of("T| |todoName","D|X|deadlineName|2024-01-31",
                    "E| |eventName|2024-01-31|2024-02-01"));
        } catch (IOException e) {
            fail();
        }
        
        Storage storage = new Storage(filePath.toString());
        try {
            TaskList tasks = storage.load();
            assertEquals(3, tasks.getCount());
            assertEquals("[T][ ] todoName", tasks.getTask(0).toString());
            assertEquals("[D][X] deadlineName (due Jan 31, 2024)", tasks.getTask(1).toString());
            assertEquals("[E][ ] eventName (Jan 31, 2024 -- Feb 1, 2024)", tasks.getTask(2).toString());
        } catch (StorageFileException e) {
            fail();
        }
    }

    @Test
    public void load_invalidTaskList_exceptionThrown(@TempDir Path tempDir) {
        Path filePath = tempDir.resolve("data.txt");
        try {
            Files.createFile(filePath);
            Files.write(filePath, "X| |unknownName\n".getBytes());
        } catch (IOException e) {
            fail();
        }

        Storage storage = new Storage(filePath.toString());
        try {
            storage.load();
            fail();
        } catch (StorageFileException e) {
            assertEquals("Corrupted storage file", e.getMessage());
        }
    }
}
