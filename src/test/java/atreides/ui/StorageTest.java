package atreides.ui;

import atreides.ui.AtreidesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void load_success() throws AtreidesException {
        Storage storage = new Storage("C:\\Users\\patyu\\Desktop\\2103\\ip\\src\\main\\atreides.ui.Atreides.txt");
        storage.load();
    }

    @Test
    public void save_fail()  {
        try {
            Storage storage = new Storage("random_file.txt");
            storage.load();
            fail();
        } catch (AtreidesException e) {
            assertEquals(e.getMessage(), "random_file.txt (The system cannot find the file specified)");
        }
    }
}
