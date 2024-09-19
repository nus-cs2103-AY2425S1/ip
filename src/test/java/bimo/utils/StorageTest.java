package bimo.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bimo.tasks.Event;
import bimo.tasks.Task;

public class StorageTest {
    @Test
    public void convertTaskToText_eventInput_success() {
        Storage storage = new Storage("data/Bimo.txt");
        Task task = new Event("Buy books", LocalDate.parse("2024-08-30"),
                LocalDate.parse("2024-08-31"));
        assertEquals("E|0|Buy books|2024-08-30/2024-08-31", storage.convertTaskToText(task));
    }
    @Test
    public void convertTextToTask_eventInput_success() {
        Storage storage = new Storage("data/Bimo.txt");
        String textLine = "E|0|go school|2024-09-03/2024-09-03";
        Task task = storage.convertTextToTask(textLine);
        assertEquals(true, task instanceof Event);
    }
}
