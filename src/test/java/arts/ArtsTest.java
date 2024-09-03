package arts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Arts application.
 * This class contains integration tests for the Arts class, verifying the application's command processing and output.
 */
public class ArtsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String filePath = "test_tasks.txt";

    /**
     * Sets up the test environment before each test.
     * Redirects System.out to capture output and ensures the test file is deleted to start fresh.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor, true, StandardCharsets.UTF_8));
        new File(filePath).delete(); // Ensure the file is clean before each test
    }

    /**
     * Tests the run() method of the Arts class by adding a todo task and listing tasks.
     * Verifies that the task is added and displayed correctly in the output.
     */
    @Test
    public void testRun_addTodoAndList() {
        String input = "todo Test Task\nlist\nbye\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Arts arts = new Arts(filePath);
        arts.run();

        String output = outputStreamCaptor.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("Test Task"));
        assertTrue(output.contains("Here are the tasks in your list:"));
        assertTrue(output.contains("1. [T][ ] Test Task"));
    }

    /**
     * Tests the run() method of the Arts class with an invalid command.
     * Verifies that the application responds with an appropriate error message.
     */
    @Test
    public void testRun_invalidCommand() {
        String input = "invalidcommand\nbye\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Arts arts = new Arts(filePath);
        arts.run();

        String output = outputStreamCaptor.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("I'm sorry, but I don't know what that means."));
    }
}
