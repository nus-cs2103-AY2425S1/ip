package socchat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import socchat.task.Task;
import socchat.task.todo.Todo;

public class StorageTest {

    @Test
    public void load_fileExist_success() throws Exception {
        File f = new File("test.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        Storage storage = new Storage("test.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        assertEquals(tasks, Storage.processStorageLine());
    }

    @Test
    public void load_fileNotExist_exceptionThrown() throws Exception {
        try {
            Storage storage = new Storage("fake_test.txt");
            ArrayList<Task> tasks = new ArrayList<>();
            assertEquals(tasks, Storage.processStorageLine());
            fail();
        } catch (SocchatException e) {
            assertEquals("Storage file not found", e.getMessage());
        }
    }

    @Test
    public void updateTest() throws IOException {
        String fileName = "update_test.txt";
        String expectedContent = "T | Not done | test check\n";
        File file = new File(fileName);
        Task t = new Todo("test check");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(t);
        file.createNewFile();

        Storage storage = new Storage(fileName);
        Storage.update(tasks, false);
        String actualContent = new String(Files.readAllBytes(Paths.get(fileName)));
        assertEquals(expectedContent, actualContent);

        file.delete();
    }
}
