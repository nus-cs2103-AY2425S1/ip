package guy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import guy.parser.Parser;


/**
 * Test class for the TaskManager class.
 */
public class TaskManagerTest {
    private static final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent =
            new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    /**
     * Sets up the output streams for testing.
     */
    @BeforeAll
    public static void start() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Resets the output streams after testing.
     */
    @AfterAll
    public static void end() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Tests the TaskManager's response to being given an input with no description.
     */
    @Test
    public void fieldlessTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("event".getBytes())));
        outContent.reset();
        p.handleCliInput();
        assertEquals("You really think I can add an EMPTY TASK!?", outContent.toString().trim());
    }
}
