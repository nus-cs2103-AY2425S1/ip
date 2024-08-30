package chobo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class StorageTest {
    private static final String TEST_FILE_PATH = "./data/test_chobo.txt";
    @Test
    void loadTasksTest() {
        try {
            FileWriter writer = new FileWriter(TEST_FILE_PATH);
            writer.write("T|0|read\n");
            writer.write("D|0|assignment|9-12-2023 0800\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to set up test file.");
            assertEquals(1,0);
        }

        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(2, tasks.size());
        assertEquals("read", tasks.get(0).getName());
        assertEquals("assignment", tasks.get(1).getName());
    }

}
