package Azir;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void read_existingFile() throws IOException {
        if (Files.exists(Paths.get("./testfile"))) {
            Files.delete(Paths.get("./testfile"));
        }
        Files.createFile(Paths.get("./testfile"));
        FileWriter writer = new FileWriter("./testfile");
        writer.write("T | Incomplete | read book\n");
        writer.write("D | Complete | submit report | Oct 10 2023\n");
        writer.write("E | Incomplete | meeting | 2pm | 4pm\n");
        writer.close();

        ArrayList<Task> result = Storage.readFileContents("./testfile");

        assertEquals("[T] [] read book", result.get(0).toString());
        assertEquals("[D] [X] submit report (by: Oct 10 2023)", result.get(1).toString());
        assertEquals("[E] [] meeting (from: 2pm to: 4pm)", result.get(2).toString());
    }

    @Test
    public void read_noFile() throws IOException {
        if (Files.exists(Paths.get("./testfile"))) {
            Files.delete(Paths.get("./testfile"));
        }
        assertThrows(FileNotFoundException.class, () -> Storage.readFileContents("./testfile"));
    }
}
