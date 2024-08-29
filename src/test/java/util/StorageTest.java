package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class StorageTest {

    @TempDir
    Path tempDir;

    /**
     * Test that the Storage class correctly reads from a file.
     */
    @Test
    public void testToArray_readsFileCorrectly() throws IOException {
        Path filePath = tempDir.resolve("testData.txt");
        List<String> lines = List.of(
                "[D][ ] Prepare report /by 2024-08-31",
                "[E][X] Attend meeting /from 2024-08-30 /to 2024-10-09",
                "[T][ ] Read book");
        Files.write(filePath, lines);

        Storage storage = new Storage(filePath.toString());
        String[] storageArray = storage.toArray();

        assertArrayEquals(lines.toArray(new String[0]), storageArray);
    }

    /**
     * Test that the Storage class handles empty files correctly.
     */
    @Test
    public void testStorage_withEmptyFile() throws IOException {
        Path filePath = tempDir.resolve("emptyFile.txt");
        Files.write(filePath, List.of());

        Storage storage = new Storage(filePath.toString());
        String[] storageArray = storage.toArray();

        assertEquals(0, storageArray.length);
    }

    /**
     * Test that the Storage class does not throw an exception for a non-existent
     * file.
     */
    @Test
    public void testStorage_withNonExistentFile() {
        Path filePath = tempDir.resolve("newFile.txt");
        assertDoesNotThrow(() -> {
            new Storage(filePath.toString());
        });
    }
}
