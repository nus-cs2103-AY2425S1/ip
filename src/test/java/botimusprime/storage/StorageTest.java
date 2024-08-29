package botimusprime.storage;

import botimusprime.tasks.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class StorageTest {
    private static final String TEST_FILE_NAME = "test_tasks.txt";
    private File testFile;

    @BeforeEach
    public void setup() {
        testFile = new File(new File("./data"), TEST_FILE_NAME);

        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @AfterEach
    public void reset() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testLoadFromDisk_FileDoesNotExist() {
        Storage storage = new Storage("fake_file.txt");
        botimusprime.tasks.TaskList taskList = storage.loadFromDisk();

        // Assert
        assertNotNull(taskList);
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    public void testLoadFromDisk_FileExistsWithValidTasks() throws IOException {
        File file = new File(new File("./data"), TEST_FILE_NAME);

        Storage storage = new Storage(TEST_FILE_NAME);



        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("T | fold clothes | false");
            writer.newLine();
            writer.write("E | national day | true | 2024-09-08 08:00 | 2024-09-08 23:00");
            writer.newLine();
            writer.write("D | return book | false | 2024-10-01 16:40");
        }

        botimusprime.tasks.TaskList taskList = storage.loadFromDisk();

        assertNotNull(taskList);
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(3, tasks.size());

        assertEquals("fold clothes", tasks.get(0)
                .getDescription());
        assertFalse(tasks.get(0).isDone());

        assertEquals("national day", tasks.get(1)
                .getDescription());
        assertTrue(tasks.get(1).isDone());

        assertEquals("return book", tasks.get(2)
                .getDescription());
        assertFalse(tasks.get(2).isDone());
    }

}
