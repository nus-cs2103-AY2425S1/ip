package yappingbot.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class StorageTest {

    @Test
    void testLoadListFromFile() {
        final Storage s = new Storage("src/test/resources/savefile_original");
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
        String savefilePath = "src/test/resources/savefile_test";
        final Storage s = new Storage(savefilePath);
        ArrayList<String> t = new ArrayList<>();
        t.add("TODO:a:false");
        t.add("TODO:b:true");
        t.add("DEADLINE:adadsdd:false:2023-12-12");
        t.add("EVENT:abc:false:2023-12-23:2025-02-01");
        s.saveListToFile(t);
        String[] expected = {
            "TODO:a:false",
            "TODO:b:true",
            "DEADLINE:adadsdd:false:2023-12-12",
            "EVENT:abc:false:2023-12-23:2025-02-01"
        };

        File saveFile = new File(savefilePath);
        Scanner scanner = new Scanner(saveFile);
        ArrayList<String> actual = new ArrayList<>();
        while (scanner.hasNext()) {
            actual.add(scanner.nextLine());
        }
        assertArrayEquals(expected, actual.toArray());
    }
}