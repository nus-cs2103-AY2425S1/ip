package yappingbot.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;

import yappingbot.tasks.*;

class StorageTest {
    @Test
    void testLoadListFromFile() throws IOException {
        Storage s = new Storage("src/test/resources/savefile_original");

        final ByteArrayOutputStream uiPrintOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(uiPrintOut));

        Task[] expected = {
                new Todo("a", false),
                new Todo("b", true),
                new Deadline("adadsdd", false, "2023-12-12"),
                new Event("abc", false, "2023-12-23","2025-02-01")
        };

        TaskList tasks;
        tasks = s.loadListFromFile();
        assertEquals(expected.length, tasks.size());

        uiPrintOut.close();
        String expected_error_out = """
                
                 |  Error encountered while loading savefile:
                 |
                 |   |  Error Reading save file! The following error was encountered: Unable to parse value - No enum constant yappingbot.tasks.TaskTypes.Ajdskljdas
                 |   |  Error Reading save file! The following error was encountered: Unable to parse value - Error Reading save file! The following error was encountered: Missing Data Values
                
                """;
        assertEquals(expected_error_out, uiPrintOut.toString());


        for (int i = 0; i < expected.length; i++) {
            assertEquals(tasks.get(i).toString(), expected[i].toString());
        }
    }

    @Test
    void testSaveListToFile() throws FileNotFoundException {
        String savefile_path = "src/test/resources/savefile_test";
        Storage s = new Storage(savefile_path);
        TaskList t = new TaskList();
        t.add(new Todo("a", false));
        t.add(new Todo("b", true));
        t.add(new Deadline("adadsdd", false, "2023-12-12"));
        t.add(new Event("abc", false, "2023-12-23","2025-02-01"));
        s.saveListToFile(t);

        String expected = """
TODO:a:false
TODO:b:true
DEADLINE:adadsdd:false:2023-12-12
EVENT:abc:false:2023-12-23:2025-02-01
                """;

        File saveFile = new File(savefile_path);
        Scanner scanner = new Scanner(saveFile);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
            sb.append("\n");
        }
        assertEquals(sb.toString(), expected);
    }
}