package yihuibot;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit Test for YihuiBot.java
 * 
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBotTest {
    private static YihuiBot yihuiBot;

    /**
     * Instantiates a new YihuiBot which will write to 'task.txt'
     * before all tests.
     */
    @BeforeAll
    public static void setUp() {
        yihuiBot = new YihuiBot("task.txt");
    }

    /**
     * Remove the created file 'task.txt' after testing all tests.
     */
    @AfterAll
    public static void cleanUp() {
        File file = new File("task.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Ensures that the bot is not null.
     */
    @Test
    public void constructor_notNull() {
        assertNotNull(yihuiBot);
    }
}
