package silverwolf.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Todo class to verify task state and string representation.
 */
public class TodoTest {

    /**
     * Tests that a Todo task's string representation is correct after being marked as done.
     */
    @Test
    void testMarkTestAsDone() {
        Task task = new Todo("Eat dinner with Robin");
        // Mark the task as done
        task.markAsDone();
        // Expected output
        String expectedOutput = " [T][X] Eat dinner with Robin";
        // Actual output
        String actualOutput = task.toString();
        // Check if they match
        assertEquals(expectedOutput, actualOutput, "The task string representation should match the expected format.");
    }

    /**
     * Tests that a Todo task's initial string representation is correct before being marked as done.
     */
    @Test
    void testInitialTodoState() {
        Task task = new Todo("Eat dinner with Robin");
        // Expected initial output (not done)
        String expectedOutput = " [T][ ] Eat dinner with Robin";
        // Actual output
        String actualOutput = task.toString();
        // Check if they match
        assertEquals(expectedOutput, actualOutput,
                "The initial task string representation should match the expected format.");
    }
}
