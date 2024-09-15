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

    /**
     * Tests the parser's response to the help command.
     */
    @Test
    void testHelp() {
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream("help".getBytes())));
        parser.handleUserInput();
        String output = newOut.toString().trim();
        assertTrue(output.contains("Available commands:"));
        assertTrue(output.contains("1. todo <task description> - Create a todo task"));
        assertTrue(output.contains("2. deadline <task description> /by <date time> - Create a deadline task"));
        assertTrue(output.contains(
                "3. event <task description> /from <start date time> /to <end date time> - Create an event task"));
        assertTrue(output.contains("4. list - Display all tasks"));
        assertTrue(output.contains("5. mark <task number> - Mark a task as done"));
        assertTrue(output.contains("6. unmark <task number> - Mark a task as not done"));
        assertTrue(output.contains("7. delete <task number> - Delete a task"));
        assertTrue(output.contains("8. find <keyword> - Find tasks containing the keyword"));
        assertTrue(output.contains(
                "9. update <task number> <new task type> <new task description> - Update an existing task"));
        assertTrue(output.contains("10. savefile <filename> - Switch to a different save file"));
        assertTrue(output.contains("11. help - Display this help message"));
        assertTrue(output.contains("12. bye - Exit the application"));
    }

    /**
     * Tests the parser's handling of GUI input for the help command.
     */
    @Test
    void testHandleGuiInputHelp() throws DukeException {
        Parser parser = new Parser(new Scanner(System.in));
        String response = parser.handleGuiInput("help");
        assertTrue(response.contains("Available commands:"));
        assertTrue(response.contains("1. todo <task description> - Create a todo task"));
        // ... Add assertions for other command descriptions ...
        assertTrue(response.contains("12. bye - Exit the application"));
    }

    @Test
    void testArchiveCommand() throws DukeException {
        // Add some tasks
        Parser parser1 = new Parser(new Scanner(new ByteArrayInputStream("todo Test task 1".getBytes())));
        parser1.handleUserInput();
        Parser parser2 = new Parser(new Scanner(new ByteArrayInputStream("todo Test task 2".getBytes())));
        parser2.handleUserInput();
        newOut.reset();

        // Execute archive command
        Parser parser3 = new Parser(new Scanner(new ByteArrayInputStream("archive".getBytes())));
        parser3.handleUserInput();

        String output = newOut.toString().trim();
        assertTrue(output.contains("Tasks archived to:"), "Should indicate tasks were archived");
        assertTrue(output.contains("Current task list has been cleared."), "Should indicate task list was cleared");

        // Check that the list is now empty
        newOut.reset();
        Parser parser4 = new Parser(new Scanner(new ByteArrayInputStream("list".getBytes())));
        parser4.handleUserInput();
        assertEquals("List is currently empty.", newOut.toString().trim(), "Task list should be empty after archiving");
    }

    @Test
    void testHandleGuiInputArchive() throws DukeException {
        Parser parser = new Parser(new Scanner(System.in));

        // Add some tasks
        parser.handleGuiInput("todo Test task 1");
        parser.handleGuiInput("todo Test task 2");

        // Execute archive command
        String response = parser.handleGuiInput("archive");
        assertTrue(response.contains("Tasks archived to:"), "Should indicate tasks were archived");
        assertTrue(response.contains("Current task list has been cleared."), "Should indicate task list was cleared");

        // Check that the list is now empty
        String listResponse = parser.handleGuiInput("list");
        assertEquals("List is currently empty.", listResponse.trim(), "Task list should be empty after archiving");
    }
}
