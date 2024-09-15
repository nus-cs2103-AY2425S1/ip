package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pandabot.tasks.Task;
import pandabot.tasks.ToDo;

/**
 * Unit tests for the Task class and its methods.
 * This class tests the functionality of the Task class and its subclasses,
 * including marking tasks as done, unmarking tasks, parsing tasks, and handling invalid inputs.
 */
class TaskTest {

    /**
     * Tests that a task can be marked as done and the status icon updates correctly.
     */
    @Test
    void testMarkAsDone() {
        Task task = new ToDo("Test task");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon(), "The task should be marked as done.");
    }

    /**
     * Tests that a task can be unmarked as not done and the status icon updates correctly.
     */
    @Test
    void testUnmark() {
        Task task = new ToDo("Test task");
        task.markAsDone();
        task.unmark();
        assertEquals(" ", task.getStatusIcon(), "The task should be unmarked (not done).");
    }

    /**
     * Tests the parse method for a valid ToDo task.
     * Ensures the ToDo task is parsed correctly and the string representation is as expected.
     */
    @Test
    void testParseToDo() {
        String[] details = {"T", "0", "Test ToDo task"};
        Task task = Task.parse(details);
        assertEquals("[T][ ] Test ToDo task", task.toString());
    }

    /**
     * Tests the parse method for a valid Deadline task.
     * Ensures the Deadline task is parsed correctly and the string representation is as expected.
     */
    @Test
    void testParseDeadline() {
        String[] details = {"D", "0", "Test Deadline task", "12/12/2024 1800"};
        Task task = Task.parse(details);
        assertEquals("[D][ ] Test Deadline task (by: Dec 12 2024, 6:00 pm)", task.toString());
    }

    /**
     * Tests the parse method for a valid Event task.
     * Ensures the Event task is parsed correctly and the string representation is as expected.
     */
    @Test
    void testParseEvent() {
        String[] details = {"E", "0", "Test Event task", "12/12/2024 1800", "13/12/2024 1800"};
        Task task = Task.parse(details);
        assertEquals("[E][ ] Test Event task (from: Dec 12 2024, 6:00 pm, to: Dec 13 2024, 6:00 pm)", task.toString());
    }

    /**
     * Tests the parse method with an invalid task type.
     * Ensures that an invalid task type results in an IllegalArgumentException.
     */
    @Test
    void testParseInvalidInput() {
        String[] details = {"X", "0", "Test Invalid task"};
        assertThrows(IllegalArgumentException.class, () -> Task.parse(details),
                "An invalid task type should throw an exception.");
    }
}
