package echobot;

import echobot.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private TaskList tasks;
    private Parser parser;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        parser = new Parser(tasks); // Initialize Parser with a new TaskList

        // Set up output stream to capture print statements
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testParseCommand() {
        String input = "todo Buy groceries";
        Command command = parser.parse(input);

        assertNotNull(command, "Command should not be null.");
        assertEquals("todo", command.getCommand(), "Command type should be 'todo'.");
    }

    @Test
    void testParseCommandWithoutDetails() {
        String input = "list";
        Command command = parser.parse(input);

        assertNotNull(command, "Command should not be null.");
        assertEquals("list", command.getCommand(), "Command type should be 'list'.");
    }

    @Test
    void testParseInvalidCommand() {
        String input = "unknown command";
        Command command = parser.parse(input); // Invalid command, expected to print error
        command.run();
        // Capture the console output and trim any trailing whitespace
        String expectedOutput = "____________________________________________________________\n"
                + " I'm sorry, I don't recognize that command.\n"
                + "____________________________________________________________\n";

        // Normalize the output by trimming extra spaces and line breaks
        String actualOutput = outputStream.toString().trim();

        assertTrue(actualOutput.contains("I'm sorry, I don't recognize that command."),
                "Should print error message for unrecognized command.");
    }

    // Restore original system output after each test
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}