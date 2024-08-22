package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.parser.Parser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class ParserTest {

    private static final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent =
            new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void resetStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testInvalidCommand1() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("go to school".getBytes())));
        outContent.reset();
        p.handleUserInput();
        assertEquals("Invalid command provided.", outContent.toString().trim());
    }

    @Test
    void testExit() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("bye".getBytes())));
        outContent.reset();
        p.handleUserInput();
//        assertEquals("Bye! Hope to see you again soon!", outContent.toString().trim());
        assertEquals("", outContent.toString().trim());
    }

}