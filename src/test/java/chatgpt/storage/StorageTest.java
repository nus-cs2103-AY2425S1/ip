package chatgpt.storage;

import chatgpt.exception.ChatBotException;
import chatgpt.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void load_fromTextFile() {
        Storage storage = new Storage("\\data\\testData.txt");
        ArrayList<Task> actual;
        String expected = "[[T][ ] todoTest, " +
                "[D][ ] deadlineTest (by: 06 Sep 2024 14:00)," +
                " [E][X] eventTest (from: 07 Sep 2024 20:00 to: 08 Sep 2024 02:00 )]";
        try {
            actual = storage.load();
            assertEquals(expected, actual.toString());
        } catch (ChatBotException e) {
            fail();
        }
    }

    @Test
    public void load_fromMissingFile_exceptionThrown() {
        Storage storage = new Storage("\\data\\missingFile.txt");
        String expected = "Problem with reading from file";
        try {
            assertEquals(expected, storage.load());
            fail();
        } catch (ChatBotException e) {
            assertEquals(expected, e.getMessage());
        }
    }
}
