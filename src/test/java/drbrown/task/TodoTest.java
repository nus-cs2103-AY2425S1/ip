package drbrown.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Todo} class.
 * This class contains unit tests for various functionalities of the {@code Todo} class,
 * including the creation of todos, handling invalid priorities, and generating string
 * representations for UI and file outputs.
 */
public class TodoTest {

    private String description;
    private String invalidPriority;
    private Task.Priority validPriority;

    /**
     * Sets up test data before each test case.
     * Initializes common test data such as task description, valid priority, and invalid priority.
     */
    @BeforeEach
    void setUp() {
        description = "Buy groceries";
        validPriority = Task.Priority.LOW;
        invalidPriority = "VERYHIGH";
    }

    /**
     * Tests the successful creation of a {@link Todo} task with a given description.
     * Ensures that the string representation of the created {@code Todo} object matches the expected format.
     */
    @Test
    public void testSuccessfulCreationTodo() {
        Todo todo = new Todo(false, description, validPriority);
        assertEquals("[T][ ] Buy groceries | LOW", todo.toString());
    }

    /**
     * Tests handling of an invalid priority when creating a {@link Todo} task.
     * Verifies that an {@code IllegalArgumentException} is thrown when an invalid priority is provided.
     */
    @Test
    public void testInvalidPriorityTodo() {
        try {
            new Todo(false, description, Task.Priority.valueOf(invalidPriority));
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException ignored) {
            // Exception is expected, so the test passes.
        }
    }

    /**
     * Tests the file string representation of a {@link Todo} task.
     * Ensures that the string representation returned by {@code toFileString()} matches the expected format.
     */
    @Test
    void testToFileStringTodo() {
        Todo todo = new Todo(false, description, validPriority);
        assertEquals("T | false | Buy groceries | LOW", todo.toFileString());
    }

    /**
     * Tests the UI string representation of a {@link Todo} task.
     * Ensures that the string representation returned by {@code toUiString()} matches the expected format.
     */
    @Test
    void testToUiStringTodo() {
        Todo todo = new Todo(false, description, validPriority);
        assertEquals("Doc, you don't just walk into a store and buy plutonium! "
                + "But you sure can add this task to your list!\n", todo.toUiString());
    }

    /**
     * Tests the successful creation of a marked (completed) {@link Todo} task.
     * Ensures that the string representation of the created {@code Todo} object when marked as done
     * matches the expected format.
     */
    @Test
    public void testSuccessfulCreationMarkDoneTodo() {
        Todo todo = new Todo(true, description, validPriority);
        assertEquals("[T][X] Buy groceries | LOW", todo.toString());
    }
}
