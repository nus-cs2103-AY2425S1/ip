package topaz.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import topaz.task.Deadline;
import topaz.task.Event;
import topaz.task.Task;
import topaz.task.Todo;

public class StorageTest {
    @Test
    void testLoad() throws IOException {
        File temp = new File("./tmp.txt");
        temp.createNewFile();
        FileWriter fw = new FileWriter(temp);
        fw.write("""
                T | 0 | read book
                D | 1 | submit assignment | 2024-08-30T18:00
                E | 0 | team meeting | 2024-08-30T09:00 | 2024-08-30T11:00""");
        fw.close();
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("read book"));
        Deadline ddl = new Deadline("submit assignment",
                LocalDateTime.of(2024, 8, 30, 18, 00));
        ddl.setDone();
        expected.add(ddl);
        expected.add(new Event("team meeting",
                LocalDateTime.of(2024, 8, 30, 9, 00),
                LocalDateTime.of(2024, 8, 30, 11, 00)));
        Storage storage = new Storage("tmp.txt");
        TaskList result = storage.load();
        assertEquals(new TaskList(expected), result, "Load incorrect return task");
    }

    @Test
    void testSave() throws IOException {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("read book"));
        Deadline ddl = new Deadline("submit assignment",
                LocalDateTime.of(2024, 8, 30, 18, 00));
        ddl.setDone();
        expected.add(ddl);
        expected.add(new Event("team meeting",
                LocalDateTime.of(2024, 8, 30, 9, 00),
                LocalDateTime.of(2024, 8, 30, 11, 00)));
        Storage storage = new Storage("tmp.txt");
        storage.save(new TaskList(expected));
        String expectedContent = """
                T | 0 | read book
                D | 1 | submit assignment | 2024-08-30T18:00
                E | 0 | team meeting | 2024-08-30T09:00 | 2024-08-30T11:00
                """;
        File f = new File("tmp.txt");
        String fileContent = Files.readString(Path.of(f.getAbsolutePath()));
        assertEquals(expectedContent.trim(), fileContent.trim(), "File content does not match expected output");
    }
}
