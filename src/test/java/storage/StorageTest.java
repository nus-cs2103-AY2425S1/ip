package storage;

import org.junit.jupiter.api.Test;
import storage.Storage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void readFromFile() throws IOException {
        Storage storage = new Storage();

        assertEquals("1. [D][X] sleep  (by: 2023-12-12 12:12 pm)\n" +
                "2. [E][_] study  (from: 2023-12-12 12:12 pm to: 2024-12-12 12:12 pm)\n" +
                "3. [D][X] sleep  (by: 2023-12-12 11:11 am)\n" +
                "4. [E][X] game  (from: 2023-11-11 11:11 am to: 2024-11-11 12:12 pm)\n" +
                "5. [T][_] hi\n" +
                "6. [T][_] omgomgomg gradle\n", storage.read().toString() );
    }
}
