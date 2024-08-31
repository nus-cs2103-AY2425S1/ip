package nah.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import nah.data.Task;
import nah.exceptions.NahException;
import nah.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    /**
     * Tests the method load.
     * The test pass if the LinkList return by method load has correct third task and
     * no exception is thrown.
     */
    @Test
    public void loadTest1() {
        String data = Paths.get("D:", "cs2103T_week_2", "src", "test", "java",
                        "nah", "storage", "storageTestFile.txt").toString();
        Storage storage = new Storage(data);
        try {
            LinkedList<Task> tasks = storage.load();
            assertEquals("[D] [ ] ip week4 (by: Sep 5 2024, 4:00 PM)", tasks.get(2).toString());
        } catch (NahException e) {
            System.out.println(e.getMessage());
            fail("Unexpected NahException");
        }
    }

}
