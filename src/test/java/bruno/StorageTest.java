package bruno;

import bruno.exceptions.BrunoException;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void load_fileNotFound() {
        try {
            Storage storage = new Storage("", "bruno.txt");
            assertEquals("", storage.loadFromFile().get(0).toString());
            fail();
        } catch (BrunoException e) {
            assertEquals("There was an error loading the data file.", e.getMessage());
        }
    }

    @Test
    public void load_fileFound() {
        try {
            Storage storage = new Storage("src/main/data/", "src/main/data/bruno.txt");
            storage.ensureDirectoryExists();
            storage.ensureFileExists();
            FileWriter fw = new FileWriter("src/main/data/bruno.txt");
            fw.write("T | [ ] | read book");
            fw.close();
            assertEquals("T | [ ] | read book", storage.loadFromFile().get(0).toString());
        } catch (BrunoException | IOException e) {
            fail();
        }
    }
}
