package patrick.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Paths;
import patrick.storage.Storage.StorageOperationException;
public class StorageTest {
    @TempDir
    public static Path testFolder;
    private static final String TEST_DATA_FOLDER = "test/data/StorageTest";
    private static final String NON_EXISTANT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws Exception {
        Storage storage = getStorage("InvalidData.txt");
        assertThrows(StorageOperationException.class, () -> storage.load());
    }

    private Storage getStorage(String fileName) throws Exception {
        return new Storage(TEST_DATA_FOLDER + "/" + fileName);
    }
}