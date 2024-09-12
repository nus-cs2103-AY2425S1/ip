package blob;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    private Storage storage = new Storage("./src/test/java/blob/StorageTest.csv");

    @Test
    public void getFileContents_gives_arrayWithSize1() {
        try {
            assertEquals(1, storage.getFileContents().size());
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve info");
        }
    }

    @Test
    public void getFileContents_retrievedCorrectContents() {
        try {
            assertEquals("[T][ ] testTodo", storage.getFileContents().get(0).toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve info");
        }
    }
}
