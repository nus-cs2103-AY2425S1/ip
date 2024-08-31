package yihuibot.storage;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.task.TaskList;

/**
 * Unit Test for Storage.java
 *
 * @author Toh Yi Hui A0259080A
 */
public class StorageTest {
    private static File file;
    private Storage storage;

    /**
     * Create new file before all tests.
     */
    @BeforeAll
    public static void setUp() {
        file = new File("data/task.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Remove the created file 'task.txt' after testing all tests.
     */
    @AfterAll
    public static void cleanUp() {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Ensures that the Storage is not null.
     */
    @Test
    public void constructor_notNull() {
        try {
            storage = new Storage("data/task.txt", "yyyy-MM-dd HH:mm");
            assertNotNull(storage);
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Ensures that load returns a TaskList.
     */
    @Test
    public void load_returnsTaskList() {
        try {
            storage = new Storage("data/task.txt", "yyyy-MM-dd HH:mm");
            assertInstanceOf(TaskList.class, storage.load());
        } catch (IOException | IncorrectTaskFormatException e) {
            fail();
        }
    }
}
