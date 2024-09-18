package optimus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Asked ChatGPT to suggest kind of tests to do
// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
// Asked ChatGPT how to test for Exception
/**
 * Test class for TaskList. Contains unit tests to verify the behavior
 * of the TaskList class, including adding tasks, marking tasks as done,
 * and handling exceptions for invalid input.
 * Exception comparison approach was adapted based on guidance from ChatGPT.
 */
public class TaskListTest {
    /**
     * Tests the addTask method to ensure an OptimusException is thrown
     * for tasks with invalid date formats.
     */
    @Test
    public void addTaskThrowsExceptionForInvalidDateFormatTest() {
        TaskList taskList = new TaskList();

        // Asked ChatGPT how to compare Exceptions
        // Test invalid date format for deadline
        Exception exception = assertThrows(OptimusException.class, () -> {
            taskList.addTask("deadline return book /by October");
        });

        assertEquals("Invalid date format for deadline. Please use yyyy-mm-dd.", exception.getMessage());

        // Test invalid date format for event
        Exception exception2 = assertThrows(OptimusException.class, () -> {
            taskList.addTask("event project meeting /from October /to December");
        });

        assertEquals("Invalid date format for Event. Please use yyyy-mm-dd.", exception2.getMessage());
    }

    /**
     * Tests the addTask method to ensure an OptimusException is thrown
     * when the description for a todo task is empty.
     */
    @Test
    public void addTaskThrowsExceptionForEmptyDescriptionTest() {
        TaskList taskList = new TaskList();

        // Test for empty description
        Exception exception = assertThrows(OptimusException.class, () -> {
            taskList.addTask("todo ");
        });

        assertEquals("The description of a todo cannot be empty >:(", exception.getMessage());
    }
}
