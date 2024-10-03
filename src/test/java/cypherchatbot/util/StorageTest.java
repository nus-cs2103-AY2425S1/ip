package cypherchatbot.util;

import cypherchatbot.CypherException;
import cypherchatbot.task.Deadline;
import cypherchatbot.task.Event;
import cypherchatbot.task.Task;
import cypherchatbot.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {
    @Test
    public void testLoadFromStorage () {
        try {
            String filepath = "./data/test.txt";

            File taskFile = new File(filepath);
            if (!taskFile.isFile()) {
                taskFile.createNewFile();
            }

            FileWriter writeToFile = new FileWriter(filepath);
            writeToFile.write("T|0|testTask1" + "\n");
            writeToFile.write("T|0|testTask2" + "\n");
            writeToFile.write("D|0|dTask|2015-11-12 14:11" + "\n");
            writeToFile.close();

            Task task1 = new ToDo("testTask1");

            assertEquals(task1,new Storage(filepath).load().get(0));
            Task task2 = new ToDo("testTask2");

            assertEquals(task2,new Storage(filepath).load().get(1));
            Task deadline = new Deadline("dTask",
                    LocalDateTime.parse("2015-11-12 14:11",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            assertEquals(deadline,new Storage(filepath).load().get(2));
        } catch (IOException | CypherException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void testLoadFromStorageWithCompletion () {
        try {
            String filepath = "./data/test.txt";

            File taskFile = new File(filepath);
            if (!taskFile.isFile()) {
                taskFile.createNewFile();
            }

            FileWriter writeToFile = new FileWriter(filepath);
            writeToFile.write("T|1|testTask1" + "\n");
            writeToFile.write("E|1|event|2022-09-09 14:00|2022-10-10 14:00" + "\n");
            writeToFile.write("D|0|dTask|2015-11-12 14:11" + "\n");
            writeToFile.close();

            Task task1 = new ToDo("testTask1");
            task1.markAsComplete();
            assertEquals(task1,new Storage(filepath).load().get(0));

            LocalDateTime from = LocalDateTime.parse("2022-09-09 14:00",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime to = LocalDateTime.parse("2022-10-10 14:00",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Task event = new Event("event", from, to);
            event.markAsComplete();
            assertEquals(event,new Storage(filepath).load().get(1));

            Task deadline = new Deadline("dTask",
                    LocalDateTime.parse("2015-11-12 14:11",
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            deadline.markAsComplete();
            deadline.markAsIncomplete();
            assertEquals(deadline,new Storage(filepath).load().get(2));
        } catch (IOException | CypherException e) {
            System.out.println(e.toString());
        }
    }
}
