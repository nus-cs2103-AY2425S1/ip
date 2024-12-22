package colress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import colress.exception.FileCorruptedException;

public class StorageTest {
    @Test
    public void loadTask_wrongBraceFormat_exceptionThrown() {
        try {
            Storage storage = new Storage("wrongBraceFormat.txt");
            storage.loadTasks(null);
            fail();
        } catch (IOException e) {
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("What is this?! The task file seems to be corrupted! "
                            + "Here, I'll create a new file for you.",
                    e.getMessage());
        }
    }

    @Test
    public void loadTask_wrongDateFormat_exceptionThrown() {
        try {
            Storage storage = new Storage("wrongDateFormat.txt");
            storage.loadTasks(null);
            fail();
        } catch (IOException e) {
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("What is this?! The task file seems to be corrupted! "
                            + "Here, I'll create a new file for you.",
                    e.getMessage());
        }
    }

    @Test
    public void loadTask_wrongTimeFormat_exceptionThrown() {
        try {
            Storage storage = new Storage("wrongTimeFormat.txt");
            storage.loadTasks(null);
            fail();
        } catch (IOException e) {
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("What is this?! The task file seems to be corrupted! "
                            + "Here, I'll create a new file for you.",
                    e.getMessage());
        }
    }
}
