package guy.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Parser class.
 */

public class ParserTest {
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
     * Tests the Parser's response to being given an invalid command.
     */
    @Test
    public void invalidTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("nutmeg".getBytes())));
        outContent.reset();
        p.handleCliInput();
        assertEquals("Maybe put in an actual command next time, shitass.", outContent.toString().trim());
    }

    /**
     * Tests the Parser's response to being given the exit command.
     */
    @Test
    public void exitTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("bye".getBytes())));
        outContent.reset();
        p.handleCliInput();
        assertEquals("", outContent.toString().trim());
    }
}
