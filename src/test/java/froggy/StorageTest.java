package froggy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void loadTasks_emptyFilePath_createNewFile() {

        // Loading task on empty file path should create new file
        String filePath = "./data/StorageTest1.txt";
        new Storage(filePath).loadTasks();
        File file = new File(filePath);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void saveTasks_todoTaskList_correctTxtFile() throws Exception {

        // Saving TaskList with Todo tasks should save correctly
        // Test uses \r\n which might not work on Unix-based systems
        String filePath = "./data/StorageTest2.txt";
        Storage storage = new Storage(filePath);
        storage.loadTasks();
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("test1"));
        taskList.add(new Todo("test2"));
        storage.saveTasks(taskList);

        File file = new File(filePath);
        String actual = Files.readString(Paths.get(filePath));
        String expected = "T 0 test1\r\nT 0 test2\r\n";
        System.out.println(actual);
        assertEquals(expected, actual);
        file.delete();
    }
}
