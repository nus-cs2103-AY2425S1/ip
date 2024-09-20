package atreides.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void loadSuccess() throws AtreidesException {
        Storage storage = new Storage("C:\\Users\\patyu\\Desktop\\2103\\ip\\src\\main\\Atreides.txt");
        storage.load();
    }

    @Test
    public void saveFail() {
        try {
            Storage storage = new Storage("random_file.txt");
            storage.load();
            fail();
        } catch (AtreidesException e) {
            assertEquals(e.getMessage(), "random_file.txt (The system cannot find the file specified)");
        }
    }
}
