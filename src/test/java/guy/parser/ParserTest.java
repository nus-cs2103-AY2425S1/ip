package guy.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import guy.exception.GuyException;


public class ParserTest {
    private static final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent =
            new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void start() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void end() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void invalidTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("nutmeg".getBytes())));
        outContent.reset();
        p.cmd();
        assertEquals("Maybe put in an actual command next time, shitass.", outContent.toString().trim());
    }

    @Test
    public void exitTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("bye".getBytes())));
        outContent.reset();
        p.cmd();
        assertEquals("", outContent.toString().trim());
    }
}
