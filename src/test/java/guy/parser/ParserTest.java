package guy.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guy.exception.GuyException;
import guy.storage.Storage;
import guy.tasks.TaskManager;
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
    @BeforeEach
    public void start() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Storage.startTest();
        TaskManager.getInstance().clearTasks();
    }

    /**
     * Resets the output streams after testing.
     */
    @AfterEach
    public void end() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        outContent.reset();
        errContent.reset();
        Storage.stopTest();
    }

    /**
     * Tests the Parser's response to being given an invalid command.
     */
    @Test
    public void invalidTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("nutmeg".getBytes())));
        p.handleCliInput();
        assertEquals("Maybe put in an actual command next time, shitass.", outContent.toString().trim());
    }

    /**
     * Tests the Parser's response to being given the exit command.
     */
    @Test
    public void exitTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("bye".getBytes())));
        assertFalse(p.handleCliInput());
        assertEquals("", outContent.toString().trim());
    }

    /**
     * Tests the Parser's response to adding a todo task.
     */
    @Test
    public void toDoTest() {
        Parser p = new Parser(new Scanner(new ByteArrayInputStream("todo break something".getBytes())));
        p.handleCliInput();
        assertTrue(outContent.toString().contains("Fine. Added this lousy task:"));
        assertTrue(outContent.toString().contains("[T] [ ] break something"));
        assertTrue(outContent.toString().contains("That's 1 tasks for your ass to handle."));
    }

    /**
     * Tests the Parser's response to adding a deadline task.
     */
    @Test
    public void deadlineTest() {
        Parser p = new Parser(new Scanner(
                new ByteArrayInputStream("deadline whack table /by 2024-09-01 12:00".getBytes())));
        p.handleCliInput();
        assertTrue(outContent.toString().contains("Fine. Added this lousy task:"));
        assertTrue(outContent.toString().contains("[D] [ ] whack table (by: 2024-09-01 12:00)"));
        assertTrue(outContent.toString().contains("That's 1 tasks for your ass to handle."));
    }

    /**
     * Tests the Parser's response to adding an event task.
     */
    @Test
    public void eventTest() {
        Parser p = new Parser(new Scanner(
                new ByteArrayInputStream("event malding /from 2024-09-02 11:00 /to 2024-09-04 19:00".getBytes())));
        p.handleCliInput();
        assertTrue(outContent.toString().contains("Fine. Added this lousy task:"));
        assertTrue(outContent.toString().contains("[E] [ ] malding (from: 2024-09-02 11:00 to: 2024-09-04 19:00)"));
        assertTrue(outContent.toString().contains("That's 1 tasks for your ass to handle."));
    }

    /**
     * Tests the Parser's response to deleting a task.
     */
    @Test
    public void deleteTest() {
        Parser testParser = new Parser(new Scanner(new ByteArrayInputStream("todo break something".getBytes())));
        testParser.handleCliInput();
        outContent.reset();

        Parser p = new Parser(new Scanner(new ByteArrayInputStream("delete 1".getBytes())));
        p.handleCliInput();

        assertTrue(outContent.toString().contains("There goes this dumb task:"));
        assertTrue(outContent.toString().contains("[T] [ ] break something"));
        assertTrue(outContent.toString().contains("Your ass still needs to handle 0 more tasks."));
    }

    /**
     * Tests the Parser's response to finding a task.
     */
    @Test
    public void findTest() {
        Parser testParser = new Parser(new Scanner(new ByteArrayInputStream("todo break something".getBytes())));
        testParser.handleCliInput();
        outContent.reset();

        testParser = new Parser(new Scanner(new ByteArrayInputStream("todo smash something".getBytes())));
        testParser.handleCliInput();
        outContent.reset();

        Parser p = new Parser(new Scanner(new ByteArrayInputStream("find smash".getBytes())));
        p.handleCliInput();

        assertTrue(outContent.toString().contains("These are your damned tasks, that actually match the keywords:"));
        assertTrue(outContent.toString().contains("[T] [ ] smash something"));
    }

    /**
     * Tests the GUI's response to an invalid command.
     */
    @Test
    public void invalidGuiTest() throws GuyException {
        Parser p = new Parser(new Scanner(System.in));
        String res = p.handleGuiInput("nutmeg");
        assertEquals("Maybe put in an actual command next time, shitass.", res.trim());
    }

    /**
     * Tests the GUI's response to adding a todo task.
     */
    @Test
    public void toDoGuiTest() throws GuyException {
        Parser p = new Parser(new Scanner(System.in));
        String res = p.handleGuiInput("todo break something");
        assertTrue(res.contains("Fine. Added this lousy task:"));
        assertTrue(res.contains("[T] [ ] break something"));
        assertTrue(res.contains("That's 1 tasks for your ass to handle."));
    }
}
