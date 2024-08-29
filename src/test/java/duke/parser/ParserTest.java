package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

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
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(newOut));
        System.setErr(new PrintStream(newErr));
        Storage.startTest();
        TaskList.getInstance().clearTasks();
    }

    /**
     * Resets the output streams after testing.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(sysOut);
        System.setErr(sysErr);
        Storage.endTest();
        newOut.reset();
        newErr.reset();
    }

    /**
     * Tests the parser's response to an invalid command.
     */
    @Test
    void testInvalidCommand() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("asdfghjkl;".getBytes())));
        parser.handleUserInput();
        assertEquals("Invalid command provided.", newOut.toString().trim());
    }

    /**
     * Tests the parser's response to the exit command.
     */
    @Test
    void testExit() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("bye".getBytes())));
        assertFalse(parser.handleUserInput());
        assertEquals("", newOut.toString().trim());
    }

    /**
     * Tests the parser's response to the todo command.
     */
    @Test
    void testTodo() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("todo read book".getBytes())));
        parser.handleUserInput();
        String expected = "Got it. I've added this task:\n"
                + "[T][ ] read book\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, newOut.toString());
    }

    /**
     * Tests the parser's response to the deadline command.
     */
    @Test
    void testDeadline() {
        Parser parser = new Parser(new Scanner(
                new ByteArrayInputStream("deadline return book /by 2024-08-23 1200".getBytes())));
        parser.handleUserInput();
        String expected = "Got it. I've added this task:\n"
                + "[D][ ] return book (by: Aug 23 2024 12:00)\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, newOut.toString());
    }

    /**
     * Tests the parser's response to the event command.
     */
    @Test
    void testEvent() {
        Parser parser = new Parser(new Scanner(
                new ByteArrayInputStream(
                        "event project meeting /from 2024-08-23 1400 /to 2024-08-23 1600".getBytes())));
        parser.handleUserInput();
        String expected = "Got it. I've added this task:\n"
                + "[E][ ] project meeting (from: Aug 23 2024 14:00 to: Aug 23 2024 16:00)\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, newOut.toString());
    }

    /**
     * Tests the parser's response to the list command with an empty list.
     */
    @Test
    void testEmptyList() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("list".getBytes())));
        parser.handleUserInput();
        assertEquals("List is currently empty.", newOut.toString().trim());
    }

    /**
     * Tests the parser's response to the list command with tasks.
     */
    @Test
    void testNonEmptyList() {
        Parser parser1 = new Parser(new Scanner(new ByteArrayInputStream("todo test task".getBytes())));
        parser1.handleUserInput(); // Add a task
        newOut.reset();

        Parser parser2 = new Parser(new Scanner(new ByteArrayInputStream("list".getBytes())));
        parser2.handleUserInput(); // List tasks
        assertTrue(newOut.toString().contains("Here are the tasks in your list:\n1. [T][ ] test task"));
    }

    /**
     * Tests the parser's response to the mark command.
     */
    @Test
    void testMark() {
        Parser parser1 = new Parser(new Scanner(new ByteArrayInputStream("todo test task".getBytes())));
        parser1.handleUserInput(); // Add a task
        newOut.reset();

        Parser parser2 = new Parser(new Scanner(new ByteArrayInputStream("mark 1".getBytes())));
        parser2.handleUserInput(); // Mark the task
        String expected = "Nice! I've marked this task as done:\n"
                + "[T][X] test task";
        assertTrue(newOut.toString().trim().contains(expected));
    }

    /**
     * Tests the parser's response to the delete command.
     */
    @Test
    void testDelete() {
        Parser parser1 = new Parser(new Scanner(new ByteArrayInputStream("todo test task".getBytes())));
        parser1.handleUserInput(); // Add a task
        newOut.reset();

        Parser parser2 = new Parser(new Scanner(new ByteArrayInputStream("delete 1".getBytes())));
        parser2.handleUserInput(); // Delete the task
        String expected = "Noted. I've removed this task:\n"
                + "[T][ ] test task\n"
                + "Now you have 0 tasks in the list.";
        assertEquals(expected, newOut.toString().trim());
    }

    /**
     * Tests the parser's handling of GUI input for a todo command.
     */
    @Test
    void testHandleGuiInputTodo() throws DukeException {
        Parser parser = new Parser(new Scanner(System.in));
        String response = parser.handleGuiInput("todo Read a book");
        String expected = "Got it. I've added this task:\n"
                + "[T][ ] Read a book\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, response);
    }

    /**
     * Tests the parser's handling of GUI input for an invalid command.
     */
    @Test
    void testHandleGuiInputInvalid() throws DukeException {
        Parser parser = new Parser(new Scanner(System.in));
        String response = parser.handleGuiInput("invalid command");
        assertEquals("Invalid command provided.", response.trim());
    }
}
