package yappingbot.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import yappingbot.tasks.*;

class StorageTest {

    @Test
    void testLoadListFromFile() throws IOException {
        Storage s = new Storage("src/test/resources/savefile_original");
        ArrayList<String> actual = s.loadListFromFile();
        String[] expected = {
                "TODO:a:false",
                "TODO:b:true",
                "Ajdskljdas:This is error:asdadd",
                "EVENT:asddadsddas:also error",
                "DEADLINE:adadsdd:false:2023-12-12",
                "EVENT:abc:false:2023-12-23:2025-02-01"
        };
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void testSaveListToFile() throws FileNotFoundException {
        String savefile_path = "src/test/resources/savefile_test";
        Storage s = new Storage(savefile_path);
        ArrayList<String> t = new ArrayList<>();
        s.saveListToFile(t);
        t.add("TODO:a:false");
        t.add("TODO:b:true");
        t.add("DEADLINE:adadsdd:false:2023-12-12");
        t.add("EVENT:abc:false:2023-12-23:2025-02-01");
        String[] expected = {
                "TODO:a:false",
                "TODO:b:true",
                "DEADLINE:adadsdd:false:2023-12-12",
                "EVENT:abc:false:2023-12-23:2025-02-01"
        };

        File saveFile = new File(savefile_path);
        Scanner scanner = new Scanner(saveFile);
        ArrayList<String> actual = new ArrayList<>();
        while (scanner.hasNext()) {
            actual.add(scanner.nextLine());
        }
        assertArrayEquals(expected, actual.toArray());
    }
}