package alex.storage;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void saveTasksToFile_validDate_success() {
        String file = "./data/test.txt";
        Storage storage = new Storage("./data", file);
        assertEquals("success", storage.saveTasksToFile(file));
    }

    @Test
    public void saveTasksToFile_invalidDate_exceptionThrown() {
        String testFails = "./data/testToFail.txt";
        assertThrows(DateTimeParseException.class,
                () -> new Storage("./data", testFails).saveTasksToFile(testFails));
    }

    @Test
    public void loadTasksFromFile_validData_success() {
        String file = "./data/test.txt";
        Storage storage = new Storage("./data", file);
        assertEquals("[T][ ] read book", storage.loadTasksFromFile(file).get(0).toString());
    }

    @Test
    public void loadTasksFromFile_invalidData_exceptionThrown() {
        String testFails = "./data/testToFail.txt";
        assertThrows(DateTimeParseException.class,
                () -> new Storage("./data", testFails).loadTasksFromFile(testFails));
    }
}
