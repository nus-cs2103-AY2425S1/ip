package easton;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void retrieveRecords_invalidFilePath_exceptionThrown() {
        try {
            Storage storage = new Storage("dummy.csv");
            File file = Paths.get(System.getProperty("user.dir"), "data", "dummy.csv").toFile();
            file.delete();

            assertThrows(IOException.class, () -> {
                storage.retrieveRecords();
            });
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    public void retrieveRecords_validFilePath_exceptionThrown() {
        try {
            Storage storage = new Storage("dummy.csv");
            assertDoesNotThrow(() -> {
                storage.retrieveRecords();
            });
        } catch (IOException e) {
            // do nothing
        }
    }


}
