package ollie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ollie.exception.OllieException;

public class StorageTest {

    /**
     * Tests the {@link Storage#loadTasks()} method.
     * Verifies that the tasks are loaded correctly from the file.
     */
    @Test
    public void loadFileTest() throws OllieException {
        Storage storage = new Storage("data/testData.txt");
        assertEquals(4, storage.loadTasks().size());
    }
}
