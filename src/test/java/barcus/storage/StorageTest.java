package barcus.storage;

import barcus.Barcus;
import barcus.tasklist.TaskList;

import barcus.exception.BarcusException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void testLoadFile() {
        try {
            Storage storage = new Storage("./data/emptyTextFile.txt");
            TaskList tasks = storage.load();
            assertEquals(0, tasks.getLength());
        } catch (BarcusException e) {
            fail();
        }
    }
}
