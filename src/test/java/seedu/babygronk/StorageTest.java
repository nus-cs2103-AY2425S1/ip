package seedu.babygronk;

import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import babygronk.Storage;

class StorageTest {

    @Test
    void testInvalidPath() throws IOException {
        Storage faultyStorage = new Storage(".///test.txt/");
        Stream<String> result = faultyStorage.init();
        assertNull(result, "Init should return null when file cannot be created.");

        File file = new File(".///test.txt");
        if (file.exists()) {
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(file.getParentFile().toPath());
        }
    }
}
