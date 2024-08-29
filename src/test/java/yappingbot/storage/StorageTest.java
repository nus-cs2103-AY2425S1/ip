package yappingbot.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import yappingbot.tasks.*;

import java.util.Collection;

class StorageTest {
    @Test
    void testLoadListFromFile() {
        Storage s = new Storage("src/test/resources/savefile_original");
        Task[] expected = {
                new Todo("a", false),
                new Todo("b", true),
                new Deadline("adadsdd", false, "2023-12-12"),
                new Event("abc", false, "2023-12-23","2025-02-01")
        };

        TaskList tasks;
        tasks = s.loadListFromFile();
        assertEquals(expected.length, tasks.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(tasks.get(i).toString(), expected[i].toString());
        }
    }

    @Test
    void testSaveListFromFile() {
        Storage s = new Storage("resources/savefile_test");
    }
}