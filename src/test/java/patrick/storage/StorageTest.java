package patrick.storage;

import org.junit.jupiter.api.Test;

import patrick.storage.Storage.StorageOperationException;
import patrick.tasklist.Task;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private static final String TEST_DATA_FOLDER = "test/data/StorageTest";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws Exception {
        Storage storage = getStorage("InvalidData.txt");
        assertThrows(StorageOperationException.class, () -> storage.load());
    }

    @Test
    public void testAddList_validTask() {
        Task task = new Task("Sample Task");
        Storage.addList(task);
        assertEquals(1, Storage.getList().size());
        assertEquals(task, Storage.getList().get(0));
    }

    private Storage getStorage(String fileName) throws Exception {
        return new Storage(TEST_DATA_FOLDER + "/" + fileName);
    }
}
