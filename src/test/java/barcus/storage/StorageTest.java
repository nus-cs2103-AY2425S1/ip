package barcus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import barcus.exception.BarcusException;
import barcus.tasklist.TaskList;

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
