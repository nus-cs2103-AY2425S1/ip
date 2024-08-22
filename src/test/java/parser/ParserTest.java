package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.DukeException;

/**
 * Test class for the Parser class.
 */
class ParserTest {

    private static final PrintStream sysOut = System.out;
    private static final PrintStream sysErr = System.err;

    private static final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream newErr = new ByteArrayOutputStream();

    /**
     * Sets up the output streams for testing.
     */
    @BeforeAll
    public static void setup() {
        System.setOut(new PrintStream(newOut));
        System.setErr(new PrintStream(newErr));
    }

    /**
     * Resets the output streams after testing.
     */
    @AfterAll
    public static void shutdown() {
        System.setOut(sysOut);
        System.setErr(sysErr);
    }

    /**
     * Tests the parser's response to an invalid command.
     */
    @Test
    void testInvalidCommand() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("asdfghjkl;".getBytes())));
        newOut.reset();
        parser.handleUserInput();
        assertEquals("Invalid command provided.", newOut.toString().trim());
    }

    /**
     * Tests the parser's response to the exit command.
     */
    @Test
    void testExit() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("bye".getBytes())));
        newOut.reset();
        parser.handleUserInput();
//        assertEquals("Bye! Hope to see you again soon!", outContent.toString().trim());
        assertEquals("", newOut.toString().trim());
    }
}