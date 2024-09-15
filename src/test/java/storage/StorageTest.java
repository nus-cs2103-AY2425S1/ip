package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import chatterboxexceptions.ChatterboxExceptions;
import parser.Parser;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;




public class StorageTest {
    private final Storage testStorage = new Storage();
    private final Parser testParser = new Parser();

    @Test
    public void storage_fileCreated() {
        Path filePath = Paths.get(testStorage.getHistFilePath());
        assertEquals(true, Files.exists(filePath));

    }

    @Test
    public void storeEvent_normalTextTodo() {
        String expectedOutput = "T | | buy groceries";
        try {
            ArrayList<Task> testList = new ArrayList<>();
            testList.add(new Todo("buy groceries"));
            testStorage.saveHistory(testList);


        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.ln("error");
        }
    }


    @Test
    public void loadEvents_loading3Tasks() {
        ArrayList<Task> input = new ArrayList<>();
        try {
            input.add(new Todo("task 1"));

            input.add(new Deadline("dead 1",
                    LocalDateTime.of(2002, 8, 29, 14, 21, 0)));

            input.add(new Event("event 1",
                    LocalDateTime.of(2003, 4, 2, 21, 12, 0),
                    LocalDateTime.of(2024, 2, 1, 12, 00, 0)));
            testStorage.saveHistory(input);

            ArrayList<Task> output = testStorage.load(testParser);
            assertEquals(input, output);


        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.ln("input error");
        } catch (FileNotFoundException e) {
            System.out.ln("file not found");
        }

    }



}
