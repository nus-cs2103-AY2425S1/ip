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
import tags.Tag;
import tags.TagList;
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
            System.out.println("error");
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

            ArrayList<Task> output = new ArrayList<>();
            TagList tags = new TagList();
            testStorage.load(testParser, output, tags);
            assertEquals(input, output);


        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("input error");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

    }

    @Test
    public void parseEvent_normalText() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            testStorage.parseTask(testParser, "E |   | event 1 ( from 4pm to 6pm )",
                   taskList, new TagList());
            assertEquals(new Event("event 1", "4pm", "6pm").getDescription(),
                   taskList.get(0).getDescription());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Test
    public void loadEvent_dateTime() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            testStorage.parseTask(testParser,
                    "E |   | event 1 ( from Apr 02 2003, 00:00 to Feb 01 2024, 00:00 )",
                    taskList, new TagList());
            assertEquals(new Event("event 1", LocalDateTime.of(2003, 04, 02, 00, 00),
                            LocalDateTime.of(2024, 02, 01, 00, 00)).getDescription(),
                    taskList.get(0).getDescription());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Test
    public void loadDead_tag() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            TagList tags = new TagList();
            testStorage.parseTask(testParser, "D |   | dead 1 ( by tmr ) /tags #tag1 #tag2",
                    taskList, tags);
            Deadline test = new Deadline("dead 1", "tmr");
            Tag tag1 = new Tag("#tag1");
            Tag tag2 = new Tag("#tag2");
            test.addTag(tag2);
            test.addTag(tag1);
            assertEquals(new Deadline("dead 1", "tmr").descNoTags(),
                    taskList.get(0).descNoTags());
            assertEquals(test.getDescription(), taskList.get(0).getDescription());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error" + e.getMessage());
        }
    }

    @Test
    public void loadEvent_tag() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            TagList tags = new TagList();
            testStorage.parseTask(testParser, "E |   | event 1 ( from 4pm to 6pm ) /tags #tag1 #tag2",
                    taskList, tags);
            assertEquals(new Event("event 1", "4pm", "6pm").descNoTags(),
                    taskList.get(0).descNoTags());
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("error" + e.getMessage());
        }
    }


}
