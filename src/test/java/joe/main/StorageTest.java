package joe.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import joe.utils.Storage;
import org.junit.jupiter.api.Test;

/**
 * Tests the Storage class.
 */
public class StorageTest {
    /**
     * Tests the Storage constructor.
     */
    @Test
    public void testStorage_invalidFilePath_exceptionThrown() {
        try {
            new Storage("src/data/test.txt");
        } catch (Exception e) {
            assertEquals("java.io.FileNotFoundException: src/data/test.txt (No such file or directory)",
                    e.toString());
        }
    }

    /**
     * Tests the load method in Storage.
     * @throws Exception if an error occurs during the loading process
     */
    @Test
    public void storageLoad_validFilePath_success() throws Exception {
        Storage storage = new Storage("data/test.txt");
        String[] lines = storage.load();
        assertEquals("T | 1 | read book", lines[0]);
        assertEquals("D | 0 | return book | by 12/12/2019 1800", lines[1]);
        assertEquals("E | 0 | project meeting | from 12/12/2019 1800 | to 12/12/2019 2100", lines[2]);
        assertEquals("T | 1 | join sports club", lines[3]);
    }
}
