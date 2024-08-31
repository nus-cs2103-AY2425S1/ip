package storage;

import chatterboxexceptions.ChatterboxExceptions;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import parser.Parser;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    Storage testStorage = new Storage();
    Parser testParser = new Parser();

    @Test
    public void storage_fileCreated() {
        Path filePath = Paths.get(testStorage.getHistFilePath());
        assertEquals(true, Files.exists(filePath));

    }

    @Test
    public void storeEvent_normalTextTODO() {
        String expectedOutput = "T | | buy groceries";
        try {
            ArrayList<Task> testList = new ArrayList<>();
            testList.add(new Todo("buy groceries"));
            testStorage.saveHistory(testList);


        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error");
        }
    }


    @Test
    public void loadEvents_loading3Tasks() {
        ArrayList<Task> input = new ArrayList<>();
        try {
            input.add(new Todo("task 1"));

            input.add(new Deadline("dead 1",
                    LocalDateTime.of(2002, 8,29,14,21,0)));

            input.add(new Event("event 1",
                    LocalDateTime.of(2003,4,2,21,12,0),
                    LocalDateTime.of(2024,2,1,12,00,0)));
            testStorage.saveHistory(input);

            ArrayList<Task> output = testStorage.load(testParser);
            assertEquals(input, output);


        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("input error");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

    }



}
