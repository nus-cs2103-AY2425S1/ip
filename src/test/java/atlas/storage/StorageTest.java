package atlas.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import atlas.exceptions.AtlasException;
import atlas.tasks.Task;

/**
 * Tests for the Storage class to ensure correct functionality for loading tasks from a file.
 */
public class StorageTest {

    /**
     * Tests that loading a valid file successfully retrieves the correct number of tasks.
     *
     * @throws AtlasException if there is an error during loading.
     */
    @Test
    public void storageLoad_validFile_success() throws AtlasException {
        Storage storage = new Storage("data/atlas.txt");
        ArrayList<Task> tasks = storage.load();
        assertEquals(tasks.size(), 6);
    }

    /**
     * Tests that attempting to load a non-existent file throws an AtlasException.
     */
    @Test
    public void storageLoad_missingFile_error() {
        Storage storage = new Storage("this/is/an/invalid/file/path/");
        assertThrows(AtlasException.class, storage::load);
    }
}
