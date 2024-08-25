package yihuibot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Test for Storage.java
 * 
 * @author Toh Yi Hui A0259080A
 */
public class StorageTest {
    private Storage storage;
    private static File file;

    /**
     * Create new file before all tests.
     */
    @BeforeAll
    public static void setUp() {
        file = new File("task.txt");
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
        storage = new Storage("task.txt", "yyyy-MM-dd HH:mm");
        assertNotNull(storage);
    }

    /**
     * Ensures that load returns a TaskList.
     */
    @Test
    public void load_returnsTaskList() {
        storage = new Storage("task.txt", "yyyy-MM-dd HH:mm");
        try {
            assertInstanceOf(TaskList.class, storage.load());
        } catch (FileNotFoundException | IncorrectTaskFormatException e) {
            fail();
        }
    }
}
