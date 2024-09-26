package chappy.util;

import org.json.simple.parser.ParseException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import chappy.task.Deadline;
import chappy.task.Event;
import chappy.task.Task;
import chappy.task.Todo;

import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StorageTest {
    @Test
    public void loadFromDisk_success() throws IOException, ParseException {
        ArrayList<Task> testList = new Storage("." + File.separator + "src" + File.separator + "test" + File.separator  + "java" + File.separator + 
                "chappy" + File.separator + "util" + File.separator + "data" + File.separator + "test_data.json").loadFromDisk();
        assertTrue(testList.size() > 0);

        for (int i = 0; i < testList.size(); i++) {
            assertTrue(testList.get(i) instanceof Event || testList.get(i) instanceof Deadline || testList.get(i) instanceof Todo);
        }
    }

    @Test
    public void loadFromDisk_ioExceptionThrown() throws IOException, ParseException {
        try {
            ArrayList<Task> testList = new Storage("." + File.separator + "src" + File.separator + "test" + File.separator  + "java" + File.separator + 
                "chappy" + File.separator + "util" + File.separator + "data" + File.separator + "test_data2.json").loadFromDisk();
            fail();
        } catch (IOException e) {
            assertEquals("File does not exist. Creating file.", e.getMessage());
            new File("." + File.separator + "src" + File.separator + "test" + File.separator  + "java" + File.separator + 
                "chappy" + File.separator + "util" + File.separator + "data" + File.separator + "test_data2.json").delete();
        }

        try {
            ArrayList<Task> testList = new Storage("." + File.separator + "src" + File.separator + "test" + File.separator  + "java" + File.separator + 
                "chappy" + File.separator + "util" + File.separator + "data" + File.separator + "test_data3.json").loadFromDisk();
            fail();
        } catch (IOException e) {
            assertEquals("File is empty.", e.getMessage());
        }

        try {
            ArrayList<Task> testList = new Storage("." + File.separator + "src" + File.separator + "test" + File.separator  + "java" + File.separator + 
                "chappy" + File.separator + "util" + File.separator + "data" + File.separator + "test_data4.json").loadFromDisk();
            fail();
        } catch (IOException e) {
            assertEquals("Error parsing JSON objects.", e.getMessage());
        }
        
       
    }
}
