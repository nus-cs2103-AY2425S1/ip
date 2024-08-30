package easton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class StorageTest {

    @Test
    public void retrieve_invalidFilePath_ExceptionThrown() {
        try {
            Storage storage = new Storage("dummy.csv");
            File file = Paths.get(System.getProperty("user.dir"), "data", "dummy.csv").toFile();
            file.delete();

            assertThrows(IOException.class, () -> {
                storage.retrieve();
            });
        } catch(IOException e) {

        }
    }

    @Test
    public void retrieve_validFilePath_ExceptionThrown() {
        try {
            Storage storage = new Storage("dummy.csv");
            assertDoesNotThrow(() -> {
                storage.retrieve();
            });
        } catch(IOException e) {

        }
    }


}
