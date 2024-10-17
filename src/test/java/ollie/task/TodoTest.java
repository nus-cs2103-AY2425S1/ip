package ollie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ollie.exception.EmptyDescriptionException;

/**
 * Tests for the {@link Todo} class.
 */
public class TodoTest {

    private Todo todoTask;

    /**
     * Initializes a {@link Todo} task instance for testing.
     */
    @BeforeEach
    public void setUp() {
        todoTask = new Todo("Read book");
    }

    /**
     * Tests the {@link Todo#createTask(String)} method with valid command input.
     * Verifies that a {@link Todo} task is correctly created.
     */
    @Test
    public void createTask_validCommand_todoTaskCreated() throws EmptyDescriptionException {
        String command = "todo Read book";
        Todo task = Todo.createTask(command);
        assertEquals("Read book", task.getDescription());
    }

    /**
     * Tests the {@link Todo#createTask(String)} method with missing description.
     * Verifies that an exception is thrown when the description is missing.
     */
    @Test
    public void createTask_missingDescription_exceptionThrown() {
        String command = "todo ";
        EmptyDescriptionException exception = assertThrows(
                EmptyDescriptionException.class, () -> Todo.createTask(command));
        assertEquals("Please add a name for a todo task! ☺", exception.getMessage());
    }

    /**
     * Tests the {@link Todo#saveAsString()} method.
     * Verifies that the task is correctly formatted for saving.
     */
    @Test
    public void saveAsString_validTodo_correctFormatReturned() {
        String expectedString = "TODO |   | Read book";
        assertEquals(expectedString, todoTask.saveAsString());
    }

    /**
     * Tests the {@link Todo#toString()} method.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void toString_validTodo_correctStringRepresentationReturned() {
        String expectedString = "[ ] [TODO] Read book";
        assertEquals(expectedString, todoTask.toString());
    }

    /**
     * Tests the {@link Todo#toString()} method.
     * Verifies that the task is correctly represented as a string.
     */
    @Test
    public void todoTest() {
        Todo todo = new Todo("Todo task");
        assertEquals("[ ] [TODO] Todo task", todo.toString());
    }
}
