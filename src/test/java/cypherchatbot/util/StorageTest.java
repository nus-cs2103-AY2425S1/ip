package cypherchatbot.util;

import cypherchatbot.task.Deadline;
import cypherchatbot.task.Task;
import cypherchatbot.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoading () {
        try {
            String filepath = "./data/test.txt";

            File taskFile = new File(filepath);
            if (!taskFile.isFile()) {
                taskFile.createNewFile();
            }

            FileWriter writeToFile = new FileWriter(filepath);
            writeToFile.write("T|1|testTask1" + "\n");
            writeToFile.write("T|0|testTask2" + "\n");
            writeToFile.write("D|0|dTask|2015-11-12 14:11" + "\n");
            writeToFile.close();

            Task task1 = new ToDo("testTask1");
            task1.completeTask();
            Task task2 = new ToDo("testTask2");
            Task deadline = new Deadline("dTask", LocalDateTime.parse("2015-11-12 14:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ArrayList<Task> output = new ArrayList<>();
            output.add(task1);
            output.add(task2);
            output.add(deadline);

            assertEquals(output,new Storage(filepath).load());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

}
