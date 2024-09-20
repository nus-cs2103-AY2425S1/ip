package easton;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void retrieveRecords_invalidFilePath_exceptionThrown() {
        try {
            Storage storage = new Storage("dummy.csv");
            File file = Paths.get(System.getProperty("user.dir"), "data", "dummy.csv").toFile();
            file.delete();

            assertThrows(IOException.class, () -> storage.retrieveRecords());
        } catch (IOException e) {
            // Irrelevant to test case
        }
    }

    @Test
    public void retrieveRecords_validFilePath_success() {
        try {
            Storage storage = new Storage("dummy.csv");
            assertDoesNotThrow(() -> {
                storage.retrieveRecords();
            });
        } catch (IOException e) {
            // Irrelevant to test case
        }
    }

    @Test
    public void saveRecords_invalidFilePath_newFileCreated() {

        ArrayList<String> records = new ArrayList<>();
        records.add("T,1,help me");
        records.add("E,0,CS2103 Class,6/9/2024 10:00,6/9/2024 11:00");
        records.add("D,1,study,23/6/2024 18:00");

        try {
            Storage storage = new Storage("dummy.csv");
            File file = Paths.get(System.getProperty("user.dir"), "data", "dummy.csv").toFile();
            file.delete();
            assertFalse(file.exists());

            storage.saveRecords(records);

            assertTrue(file.exists());
        } catch (IOException e) {
            // Irrelevant to test case
        }
    }

    @Test
    public void saveRecords_validFilePath_success() {

        ArrayList<String> records = new ArrayList<>();
        records.add("T,1,help me");
        records.add("E,0,CS2103 Class,6/9/2024 10:00,6/9/2024 11:00");
        records.add("D,1,study,23/6/2024 18:00");

        try {
            Storage storage = new Storage("dummy.csv");
            assertDoesNotThrow(() -> storage.saveRecords(records));
        } catch (IOException e) {
            // Irrelevant to test case
        }
    }
}
