package drbrown.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Todo class.
 * Tests the creation, string representations, and status handling for Todo tasks.
 */
public class TodoTest {

    private String description;

    /**
     * Sets up test data before each test case.
     */
    @BeforeEach
    void setUp() {
        description = "Buy groceries";
    }

    /**
     * Tests successful creation of a Todo task with the given description.
     */
    @Test
    public void testSuccessfulCreationTodo() {
        Todo todo = new Todo(false, description);
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    /**
     * Tests the file string representation of a Todo task.
     */
    @Test
    void testToFileStringTodo() {
        Todo todo = new Todo(false, description);
        assertEquals("T | false | Buy groceries", todo.toFileString());
    }

    /**
     * Tests the UI string representation of a Todo task.
     */
    @Test
    void testToUIStringTodo() {
        Todo todo = new Todo(false, description);
        assertEquals("Doc, you don't just walk into a store and buy plutonium! "
                + "But you sure can add this task to your list!\n", todo.toUIString());
    }

    /**
     * Tests successful creation of a marked (completed) Todo task.
     */
    @Test
    public void testSuccessfulCreationMarkDoneTodo() {
        Todo todo = new Todo(true, description);
        assertEquals("[T][X] Buy groceries", todo.toString());
    }
}
