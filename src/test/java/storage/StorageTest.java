package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void readFromFile() throws IOException {
        Storage storage = new Storage();
        File file = new File(".\\src\\main\\data\\task_list.txt");
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.nextLine() + "\n");
        }

        assertEquals(sb.toString(), storage.read().toString());
    }
}
