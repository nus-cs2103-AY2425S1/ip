package atlas.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import atlas.exceptions.AtlasException;
import atlas.tasks.Task;

public class StorageTest {
    @Test
    public void storageLoad_validFile_success() throws AtlasException {
        Storage storage = new Storage("data/atlas.txt");
        ArrayList<Task> tasks = storage.load();
        assertEquals(tasks.size(), 6);
    }

    @Test
    public void storageLoad_missingFile_error() {
        Storage storage = new Storage("this/is/an/invalid/file/path/");
        assertThrows(AtlasException.class, storage::load);
    }
}
